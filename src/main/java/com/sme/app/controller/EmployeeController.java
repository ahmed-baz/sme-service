package com.sme.app.controller;

import com.sme.app.criteria.EmployeeCriteria;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.permission.annotations.MakerOnly;
import com.sme.app.permission.annotations.SuperAdminOnly;
import com.sme.app.service.EmployeeService;
import com.sme.app.service.UserExecutorService;
import com.sme.app.vo.employee.EmployeeSalaryVo;
import com.sme.app.vo.employee.EmployeeVo;
import com.sme.app.vo.payload.AppResponse;
import com.sme.app.vo.payload.PageResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    private final UserExecutorService userExecutorService;

    @PostMapping("/list/db/{size}")
    public AppResponse<PageResponse<EmployeeVo>> createEmployeeList(@PathVariable int size) {
        List<EmployeeVo> employeeList = userExecutorService.createEmployeeList(size);
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @GetMapping("all")
    public AppResponse<PageResponse<EmployeeVo>> findList() {
        List<EmployeeVo> employeeList = employeeService.findList();
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @GetMapping("search")
    public AppResponse<PageResponse<EmployeeVo>> search(@RequestBody EmployeeCriteria criteria) {
        PageResponse<EmployeeVo> pageResponse = employeeService.search(criteria);
        return new AppResponse<>(pageResponse);
    }

    @GetMapping
    public AppResponse<PageResponse<EmployeeVo>> findList(@RequestParam String sme) {
        logger.log(Level.INFO, "find employee list by SME named {}", sme);
        List<EmployeeVo> employeeList = employeeService.findListBySmeName(sme);
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @MakerOnly
    @PostMapping("/{smeCode}")
    public AppResponse<EmployeeVo> createEmployee(@PathVariable String smeCode, @RequestBody EmployeeVo employeeVo) {
        return new AppResponse<>(employeeService.createEmployee(smeCode, employeeVo));
    }

    @PostMapping("/list/async/{size}")
    public AppResponse<Void> createEmployeeListAsync(@PathVariable int size) {
        employeeService.createEmployeeListAsync(size);
        return new AppResponse<>();
    }

    @GetMapping("/{id}")
    public AppResponse<EmployeeVo> findEmployeeById(@PathVariable Long id) {
        return new AppResponse<>(employeeService.findById(id));
    }

    @GetMapping("/email/{email}")
    public AppResponse<EmployeeVo> findEmployeeByEmail(@PathVariable String email) {
        return new AppResponse<>(employeeService.findByEmail(email));
    }

    @GetMapping("/find/{id}")
    @CircuitBreaker(name = "findEmpById-api", fallbackMethod = "getDefaultIntResponse")
    @Retry(name = "findEmpById-api", fallbackMethod = "getDefaultIntResponse")
    @RateLimiter(name = "findEmpById-api")
    @Bulkhead(name = "findEmpById-api")
    public AppResponse<EmployeeVO> findEmployee(@PathVariable Long id) {
        return new AppResponse<>(employeeService.findEmpById(id));
    }

    @DeleteMapping("/{id}")
    public AppResponse<Boolean> deleteEmployee(@PathVariable Long id) {
        return new AppResponse<>(employeeService.deleteEmployee(id));
    }

    @SuperAdminOnly
    @GetMapping("/mv/salary")
    public AppResponse<List<EmployeeSalaryVo>> getSalariesCountMV() {
        return new AppResponse<>(employeeService.getSalariesCountMV());
    }

    @SuperAdminOnly
    @GetMapping("/view/salary")
    public AppResponse<List<EmployeeSalaryVo>> getSalariesCount() {
        return new AppResponse<>(employeeService.getSalariesCount());
    }

    @SuperAdminOnly
    @GetMapping("/refresh-view")
    public AppResponse<Void> refreshView() {
        employeeService.refreshView();
        return new AppResponse<>();
    }

    private AppResponse<Void> getDefaultIntResponse(Throwable throwable) {
        return new AppResponse<>(AppErrorKeys.SERVICE_DOWN, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
