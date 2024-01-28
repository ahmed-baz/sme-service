package com.sme.app.controller;

import com.sme.app.permission.annotations.SuperAdminOnly;
import com.sme.app.service.DashboardService;
import com.sme.app.vo.employee.EmployeeSalaryVo;
import com.sme.app.vo.payload.AppResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @SuperAdminOnly
    @GetMapping("/mv/salary")
    public AppResponse<List<EmployeeSalaryVo>> getSalariesCountMV() {
        return new AppResponse<>(dashboardService.getSalariesCountMV());
    }

    @SuperAdminOnly
    @GetMapping("/view/salary")
    public AppResponse<List<EmployeeSalaryVo>> getSalariesCount() {
        return new AppResponse<>(dashboardService.getSalariesCount());
    }

    @SuperAdminOnly
    @GetMapping("/refresh-view")
    public AppResponse<Void> refreshView() {
        dashboardService.refreshView();
        return new AppResponse<>();
    }

}
