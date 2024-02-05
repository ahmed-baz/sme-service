package com.sme.app.controller;

import com.sme.app.criteria.EmployeeCriteria;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.integration.model.EmployeeVO;
import com.sme.app.permission.annotations.MakerOnly;
import com.sme.app.service.EmployeeExecutorService;
import com.sme.app.service.EmployeeService;
import com.sme.app.vo.employee.EmployeeVo;
import com.sme.app.vo.payload.AppResponse;
import com.sme.app.vo.payload.PageResponse;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sme.app.exception.AppErrorKeys.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
@Validated
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    private final EmployeeExecutorService employeeExecutorService;

    @PostMapping("/list/db/{size}")
    public AppResponse<PageResponse<EmployeeVo>> createEmployeeList(@PathVariable int size) {
        List<EmployeeVo> employeeList = employeeExecutorService.createEmployeeList(size);
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @GetMapping("all")
    public AppResponse<PageResponse<EmployeeVo>> findList() {
        List<EmployeeVo> employeeList = employeeService.findList();
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @GetMapping("search")
    public AppResponse<PageResponse<EmployeeVo>> search(@Valid @RequestBody EmployeeCriteria criteria) {
        PageResponse<EmployeeVo> pageResponse = employeeService.search(criteria);
        return new AppResponse<>(pageResponse);
    }

    @GetMapping("statistics")
    public AppResponse<PageResponse<EmployeeVo>> getEmployeeSalaryStatistics(@Valid @RequestBody EmployeeCriteria criteria) {
        List<EmployeeVo> employeeList = employeeService.getEmployeeSalaryStatistics(criteria);
        return new AppResponse<>(new PageResponse<>(employeeList));
    }

    @GetMapping
    public AppResponse<PageResponse<EmployeeVo>> findList(@NotEmpty(message = SME_IS_REQUIRED) @RequestParam String sme,
                                                          @NotNull(message = PAGE_NUMBER_IS_REQUIRED) @PositiveOrZero(message = INVALID_PAGE_NUMBER) @RequestParam Integer index,
                                                          @NotNull(message = PAGE_SIZE_IS_REQUIRED) @Positive(message = INVALID_PAGE_SIZE) @RequestParam Integer size) {
        logger.log(Level.INFO, "find employee list by SME named {}", sme);
        PageResponse<EmployeeVo> pageResponse = employeeService.findListBySmeName(sme, index, size);
        return new AppResponse<>(pageResponse);
    }

    @MakerOnly
    @PostMapping("/{smeCode}")
    public AppResponse<EmployeeVo> createEmployee(@PathVariable String smeCode, @Valid @RequestBody EmployeeVo employeeVo) {
        return new AppResponse<>(employeeService.createEmployee(smeCode, employeeVo));
    }

    @PostMapping("/list/async/{size}")
    public AppResponse<Void> createEmployeeListAsync(@PathVariable int size) {
        employeeExecutorService.createEmployeeListAsync(size);
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

    @PutMapping
    public AppResponse<EmployeeVo> updateEmployee(@RequestBody EmployeeVo employeeVo) {
        return new AppResponse<>(employeeService.updateEmployee(employeeVo));
    }

    @GetMapping("dummy")
    public AppResponse<EmployeeVo> doDummyUpdate() {
        return new AppResponse<>(employeeService.doDummyUpdate());
    }

    private AppResponse<Void> getDefaultIntResponse(Throwable throwable) {
        return new AppResponse<>(AppErrorKeys.SERVICE_DOWN, HttpStatus.SERVICE_UNAVAILABLE);
    }


}
