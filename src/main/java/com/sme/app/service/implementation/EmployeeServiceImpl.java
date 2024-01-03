package com.sme.app.service.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sme.app.criteria.EmployeeCriteria;
import com.sme.app.entity.Sme;
import com.sme.app.entity.employee.Employee;
import com.sme.app.entity.employee.EmployeeSalaryMV;
import com.sme.app.entity.employee.EmployeeSalaryView;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.integration.client.EmployeeClient;
import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.mapper.BaseMapper;
import com.sme.app.mapper.EmployeeMapper;
import com.sme.app.mapper.SmeMapper;
import com.sme.app.repo.BaseRepo;
import com.sme.app.repo.SmeRepo;
import com.sme.app.repo.employee.EmployeeRepo;
import com.sme.app.repo.employee.EmployeeSalaryMVRepo;
import com.sme.app.repo.employee.EmployeeSalaryViewRepo;
import com.sme.app.service.EmployeeService;
import com.sme.app.utils.EmployeeUtil;
import com.sme.app.vo.SmeVo;
import com.sme.app.vo.employee.EmployeeSalaryVo;
import com.sme.app.vo.employee.EmployeeVo;
import com.sme.app.vo.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl extends SmeManagerImpl<Employee, EmployeeVo, EmployeeCriteria> implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final RestTemplate restTemplate;
    @Value("${emp.service.base.url}")
    private String empServiceBaseUrl;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
    private final EmployeeClient employeeClient;
    private final EmployeeSalaryViewRepo employeeSalaryViewRepo;
    private final EmployeeSalaryMVRepo employeeSalaryMVRepo;
    @Autowired
    private SmeRepo smeRepo;
    @Autowired
    private SmeMapper smeMapper;

    @Async
    @Override
    public void createEmployeeListAsync(int userNo) {
        List<Sme> smes = smeRepo.findAll();
        List<EmployeeVo> employeeList = EmployeeUtil.getEmployeeList(userNo, 1);
        List<Employee> employees = employeeMapper.voListToEntityList(employeeList);
        employees.forEach(employee -> employee.setSme(anySme(smes)));
        employeeRepo.saveAll(employees);
    }

    public Sme anySme(List<Sme> smes) {
        Random random = new Random();
        int index = random.nextInt(smes.size());
        return smes.get(index);
    }

    @Override
    public EmployeeVo findById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            return employeeMapper.entityToVo(employee.get());
        }
        throw new AppExceptionResponse(AppErrorKeys.EMPLOYEE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @Override
    public EmployeeVo createEmployee(EmployeeVo employeeVo) {
        return add(employeeVo);
    }

    @Override
    public EmployeeVO findEmpById(Long id) {
        AppResponse<EmployeeVO> response = employeeClient.findEmployeeById(id);
        if (HttpStatus.OK.equals(response.getStatus())) {
            return response.getData();
        }
        String errorCode = response.getErrorCode();
        throw new AppExceptionResponse(errorCode, HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        delete(id);
        return true;
    }

    @Override
    public EmployeeVO findEmployeeById(Long id) {
        String url = empServiceBaseUrl + "employee/" + id;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        Type typeMyType = new TypeToken<AppResponse<EmployeeVO>>() {
        }.getType();
        AppResponse<EmployeeVO> appResponse = gson.fromJson(responseEntity.getBody(), typeMyType);
        if (appResponse != null && HttpStatus.OK.equals(appResponse.getStatus()) && appResponse.getData() != null) {
            return appResponse.getData();
        } else if (appResponse != null) {
            String errorCode = appResponse.getErrorCode();
            throw new AppExceptionResponse(errorCode, HttpStatus.BAD_REQUEST);
        }
        throw new AppExceptionResponse(AppErrorKeys.EMPLOYEE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @Override
    public List<EmployeeSalaryVo> getSalariesCountMV() {
        List<EmployeeSalaryMV> all = employeeSalaryMVRepo.findAll();
        List<EmployeeSalaryVo> list = all.stream().map(data -> EmployeeSalaryVo.builder().salary(data.getSalary()).count(data.getCount()).sum(data.getSum()).build()).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<EmployeeSalaryVo> getSalariesCount() {
        List<EmployeeSalaryView> all = employeeSalaryViewRepo.findAll();
        List<EmployeeSalaryVo> list = all.stream().map(data -> EmployeeSalaryVo.builder().salary(data.getSalary()).count(data.getCount()).sum(data.getSum()).build()).collect(Collectors.toList());
        return list;
    }

    @Override
    public void refreshView() {
        employeeSalaryMVRepo.refreshView();
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
