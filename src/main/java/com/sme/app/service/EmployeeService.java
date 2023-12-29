package com.sme.app.service;

import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.integration.client.EmployeeClient;
import com.sme.app.model.Employee;
import com.sme.app.repo.EmployeeRepo;
import com.sme.app.vo.AppResponse;
import com.sme.app.integration.model.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final RestTemplate restTemplate;
    @Value("${emp.service.base.url}")
    private String empServiceBaseUrl;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
    private final EmployeeClient employeeClient;

    @Async
    public void createEmployeeListAsync(int userNo) {
        List<Employee> employeeList = EmployeeUtil.getEmployeeList(userNo, 1);
        employeeRepo.saveAll(employeeList);
    }

    public Employee findById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new AppExceptionResponse(AppErrorKeys.EMPLOYEE_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    public EmployeeVO findEmpById(Long id) {
        AppResponse<EmployeeVO> response = employeeClient.findEmployeeById(id);
        if (HttpStatus.OK.equals(response.getStatus())) {
            return response.getData();
        }
        String errorCode = response.getErrorCode();
        throw new AppExceptionResponse(errorCode, HttpStatus.BAD_REQUEST);
    }

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

}
