package com.sme.app.schedule;


import com.sme.app.entity.Sme;
import com.sme.app.entity.employee.Employee;
import com.sme.app.repo.SmeRepo;
import com.sme.app.repo.employee.EmployeeRepo;
import com.sme.app.service.SmeService;
import com.sme.app.utils.SmeUtil;
import com.sme.app.vo.SmeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmployeeScheduleService {

    private final EmployeeRepo employeeRepo;
    private final SmeService smeService;
    @Value("${jobs.update.employee.enable}")
    private boolean updateEmployeeJob;

    @Scheduled(cron = "${employee.update.cron}")
    public void updateEmployeeWithDummySme() {
        if (updateEmployeeJob) {
            List<Employee> employees = employeeRepo.findBySmeIsNull();
            if (!employees.isEmpty()) {
                List<SmeVo> smes = smeService.findAllSmes();
                employees.forEach(employee -> {
                    employee.setSme(SmeUtil.anySme(smes));
                    employeeRepo.save(employee);
                });
            }
        }
    }

}
