package com.sme.app.service.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sme.app.criteria.EmployeeCriteria;
import com.sme.app.entity.employee.Employee;
import com.sme.app.entity.employee.EmployeeView;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.integration.client.EmployeeClient;
import com.sme.app.integration.model.SmeEmployeeVO;
import com.sme.app.mapper.BaseMapper;
import com.sme.app.mapper.EmployeeMapper;
import com.sme.app.repo.BaseRepo;
import com.sme.app.repo.employee.EmployeeRepo;
import com.sme.app.service.EmployeeService;
import com.sme.app.service.SmeService;
import com.sme.app.specification.EmployeeSpecifications;
import com.sme.app.vo.SmeVo;
import com.sme.app.vo.employee.EmployeeVo;
import com.sme.app.vo.payload.AppResponse;
import com.sme.app.vo.payload.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployeeServiceImpl extends SmeManagerImpl<Employee, EmployeeVo, EmployeeCriteria> implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final RestTemplate restTemplate;
    @Value("${emp.service.base.url}")
    private String empServiceBaseUrl;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
    private final EmployeeClient employeeClient;
    private final SmeService smeService;
    private final EmployeeServiceRPC employeeServiceRPC;

    @Override
    public EmployeeVo findById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            return employeeMapper.entityToVo(employee.get());
        }
        throw new AppExceptionResponse(AppErrorKeys.EMPLOYEE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<EmployeeVo> findList() {
        List<Employee> all = employeeRepo.findAll();
        return employeeMapper.entityListToVoList(all);
    }

    @Override
    public PageResponse<EmployeeVo> search(EmployeeCriteria criteria) {
        Specification<Employee> employeeSpec = EmployeeSpecifications.createEmployeeSpec(criteria);
        PageRequest pageRequest = preparePageable(criteria);
        Page<Employee> all = employeeRepo.findAll(employeeSpec, pageRequest);
        return employeeMapper.preparePageResponse(all);
    }

    @Override
    public List<EmployeeVo> getEmployeeSalaryStatistics(EmployeeCriteria criteria) {
        Specification<Employee> employeeSpec = EmployeeSpecifications.createEmployeeSpec(criteria);
        criteria.setPageNumber(0);
        PageRequest pageRequest = preparePageable(criteria, "salary", Sort.Direction.DESC);
        Page<Employee> all = employeeRepo.findAll(employeeSpec, pageRequest);
        return employeeMapper.entityListToVoList(all.getContent());
    }

    @Override
    public PageResponse<EmployeeVo> findListBySmeName(String name, int index, int size) {
        PageRequest pageRequest = PageRequest.of(index, size);
        Page<Employee> all = employeeRepo.findBySmeNameIgnoreCaseContaining(name, pageRequest);
        return employeeMapper.preparePageResponse(all);
    }

    @Override
    public EmployeeVo findByEmail(String email) {
        Optional<EmployeeView> employee = employeeRepo.findEmployeeByEmail(email);
        if (employee.isPresent()) {
            return employeeMapper.fromEmployeeViewToEmployeeVO(employee.get());
        }
        throw new AppExceptionResponse(AppErrorKeys.EMPLOYEE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @Override
    public EmployeeVo createEmployee(String smeCode, EmployeeVo employeeVo) {
        List<SmeVo> smes = smeService.findAllSmes();
        Optional<SmeVo> smeVo = smes.stream().filter(sme -> smeCode.equals(sme.getCode())).findFirst();
        if (smeVo.isPresent()) {
            employeeVo.setSme(smeVo.get());
        } else {
            throw new AppExceptionResponse(AppErrorKeys.INVALID_SME, HttpStatus.BAD_REQUEST);
        }
        return add(employeeVo);
    }

    @Override
    public EmployeeVo updateEmployee(EmployeeVo employeeVo) {
        return update(employeeVo.getId(), employeeVo);
    }

    @Override
    public EmployeeVo doDummyUpdate() {
        Employee employee = employeeRepo.findRandom();
        AppResponse<SmeEmployeeVO> appResponse = employeeClient.findByEmail(employee.getEmail());
        SmeEmployeeVO empData = appResponse.getData();
        employee.setSalary(empData.getSalary());
        employeeRepo.save(employee);
        return employeeMapper.entityToVo(employee);
    }

    @Override
    public SmeEmployeeVO findEmpById(Long id) {
        String errorCode = null;
        try {
            AppResponse<SmeEmployeeVO> response = employeeClient.findEmployeeById(id);
            if (HttpStatus.OK.equals(response.getStatus())) {
                return response.getData();
            }
            errorCode = response.getErrorCode();
        } catch (Exception ex) {
            log.error(ex);
        }
        throw new AppExceptionResponse(errorCode != null ? errorCode : AppErrorKeys.INTEGRATION_ISSUE, HttpStatus.BAD_REQUEST);
    }

    @Override
    public EmployeeVo findStaffById(Long id) {
        return employeeServiceRPC.findById(id);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        delete(id);
        return true;
    }

    @Override
    public SmeEmployeeVO findEmployeeById(Long id) {
        String url = empServiceBaseUrl + "employee/" + id;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        Type typeMyType = new TypeToken<AppResponse<SmeEmployeeVO>>() {
        }.getType();
        AppResponse<SmeEmployeeVO> appResponse = gson.fromJson(responseEntity.getBody(), typeMyType);
        if (appResponse != null && HttpStatus.OK.equals(appResponse.getStatus()) && appResponse.getData() != null) {
            return appResponse.getData();
        } else if (appResponse != null) {
            String errorCode = appResponse.getErrorCode();
            throw new AppExceptionResponse(errorCode, HttpStatus.BAD_REQUEST);
        }
        throw new AppExceptionResponse(AppErrorKeys.EMPLOYEE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @Override
    public BaseMapper<Employee, EmployeeVo> getMapper() {
        return employeeMapper;
    }

    @Override
    public BaseRepo<Employee> getRepo() {
        return employeeRepo;
    }

}
