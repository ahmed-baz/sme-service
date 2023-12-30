package com.sme.app.service.implementation;

import com.sme.app.model.Employee;
import com.sme.app.repo.EmployeeRepo;
import com.sme.app.service.UserExecutorService;
import com.sme.app.utils.EmployeeUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Setter
@Getter
@Log4j2
@Service
public class UserExecutorServiceImpl implements UserExecutorService {

    @Autowired
    private EmployeeRepo employeeRepo;
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final List<Callable<List<Employee>>> callables = new ArrayList<>();

    Runnable runnableTask = () -> {
        long start = System.nanoTime();
        try {
            List<Employee> employeeList = EmployeeUtil.getEmployeeList(10, 1);
            employeeRepo.saveAll(employeeList);
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            long seconds = Duration.ofNanos(System.nanoTime() - start).getSeconds();
            log.info("TOTAL_TIME=" + seconds);
        }
    };

    Callable<List<Employee>> callableTask = () -> {
        List<Employee> employeeList = new ArrayList<>();
        long start = System.nanoTime();
        try {
            employeeList = EmployeeUtil.getEmployeeList(10, 1);
            employeeList = employeeRepo.saveAll(employeeList);
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            long seconds = Duration.ofNanos(System.nanoTime() - start).getSeconds();
            log.info("TOTAL_TIME=" + seconds);
        }
        return employeeList;
    };

    public UserExecutorServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @SneakyThrows
    @Override
    public List<Employee> createEmployeeList(int size) {
        List<Future<List<Employee>>> futureList = new ArrayList<>();
        DummyEmployeeService dummyEmployeeService = new DummyEmployeeService();
        int threads = size / 10;
        dummyEmployeeService.setSize(threads);
        for (int i = 0; i < 10; i++) {
            Future<List<Employee>> future = executorService.submit(dummyEmployeeService);
            futureList.add(future);
        }
        return saveEmployeeList(futureList);
    }

    private List<Employee> saveEmployeeList(List<Future<List<Employee>>> futureList) {
        List<Employee> total = new ArrayList<>();
        futureList.forEach(listFuture -> {
            try {
                List<Employee> employees = listFuture.get();
                List<Employee> savedEmps = employeeRepo.saveAll(employees);
                total.addAll(savedEmps);
            } catch (InterruptedException e) {
                log.error(e);
            } catch (ExecutionException e) {
                log.error(e);
            }
        });
        return total;
    }


}
