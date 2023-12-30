package com.sme.app.service.implementation;

import com.sme.app.entity.Employee;
import com.sme.app.mapper.EmployeeMapper;
import com.sme.app.repo.EmployeeRepo;
import com.sme.app.service.UserExecutorService;
import com.sme.app.utils.EmployeeUtil;
import com.sme.app.vo.EmployeeVo;
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

    private Integer limit = 10;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeMapper employeeMapper;
    private final ExecutorService executorService = Executors.newFixedThreadPool(limit);
    private final List<Callable<List<Employee>>> callables = new ArrayList<>();

    Runnable runnableTask = () -> {
        long start = System.nanoTime();
        try {
            List<EmployeeVo> employeeList = EmployeeUtil.getEmployeeList(10, 1);
            employeeRepo.saveAll(employeeMapper.voListToEntityList(employeeList));
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            long seconds = Duration.ofNanos(System.nanoTime() - start).getSeconds();
            log.info("TOTAL_TIME=" + seconds);
        }
    };

    Callable<List<EmployeeVo>> callableTask = () -> {
        List<EmployeeVo> employeeList = new ArrayList<>();
        long start = System.nanoTime();
        try {
            employeeList = EmployeeUtil.getEmployeeList(10, 1);
            List<Employee> employees = employeeRepo.saveAll(employeeMapper.voListToEntityList(employeeList));
            employeeList = employeeMapper.entityListToVoList(employees);
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
    public List<EmployeeVo> createEmployeeList(int size) {
        List<Future<List<EmployeeVo>>> futureList = new ArrayList<>();
        DummyEmployeeService dummyEmployeeService = new DummyEmployeeService();
        int threads = size / limit;
        limit = threads > 0 ? limit : size;
        dummyEmployeeService.setSize(threads > 0 ? threads : size);
        for (int i = 0; i < this.limit; i++) {
            Future<List<EmployeeVo>> future = executorService.submit(dummyEmployeeService);
            futureList.add(future);
        }
        return saveEmployeeList(futureList);
    }

    private List<EmployeeVo> saveEmployeeList(List<Future<List<EmployeeVo>>> futureList) {
        List<Employee> total = new ArrayList<>();
        for (Future<List<EmployeeVo>> listFuture : futureList) {
            try {
                List<EmployeeVo> employees = listFuture.get();
                List<Employee> savedEmps = employeeRepo.saveAll(employeeMapper.voListToEntityList(employees));
                total.addAll(savedEmps);
            } catch (InterruptedException e) {
                log.error(e);
            } catch (ExecutionException e) {
                log.error(e);
            }
        }
        return employeeMapper.entityListToVoList(total);
    }


}
