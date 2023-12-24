package com.demo.user.service;

import com.demo.user.feign.EmployeeProxy;
import com.demo.user.model.Employee;
import com.demo.user.repo.EmployeeRepo;
import com.demo.user.vo.BaseData;
import com.demo.user.vo.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final String outputHeader = "ID,FIRST_NAME,LAST_NAME,EMAIL,SALARY \n";
    private final String reportHeader = "EMAIL,FOUND \n";
    @Value("${user.number}")
    private int userNo;
    private final EmployeeRepo employeeRepo;
    @Value("${employees.sheet.path}")
    private String employeesSheetPath;
    @Value("${report.path}")
    private String reportSheetPath;
    private long start;
    private long end;
    private RestTemplate restTemplate = new RestTemplate();
    private final static String baseUrl = "http://localhost:9999/employee/";
    private Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
    private final EmployeeProxy employeeProxy;

    public List<Employee> createEmployeeList() {
        List<Employee> employeeList = EmployeeUtil.getEmployeeList(userNo);
        return employeeRepo.saveAll(employeeList);
    }

    @Async
    public void createEmployeeListAsync() {
        List<Employee> employeeList = EmployeeUtil.getEmployeeList(userNo, 1);
        employeeRepo.saveAll(employeeList);
    }

    public List<Employee> addEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            //start = System.currentTimeMillis();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(employeesSheetPath), StandardCharsets.UTF_8));
            writer.write(outputHeader);
            employeeList = EmployeeUtil.getEmployeeList(userNo);
            saveEmployeeList(employeeList);
            for (Employee employee : employeeList) {
                StringBuilder builder = new StringBuilder(String.valueOf(employee.getId())).append(",");
                String newRow = builder.append(employee.getFirstName()).append(",")
                        .append(employee.getLastName()).append(",")
                        .append(employee.getEmail()).append("\n").toString();
                writer.write(newRow);
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println("An error occurred.");
        } finally {
            // end = System.currentTimeMillis();
        }
        return employeeList;
    }

    private void saveEmployeeList(List<Employee> employeeList) {
        employeeRepo.saveAll(employeeList);
    }

    public long validateEmployees() {
        Map<String, Integer> mailCount = new HashMap<>();
        start = System.currentTimeMillis();
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reportSheetPath), StandardCharsets.UTF_8));
            Scanner empScanner = new Scanner(new File(employeesSheetPath), "UTF-8");
            empScanner.nextLine();
            writer.write(reportHeader);
            while (empScanner.hasNextLine()) {
                String row = empScanner.nextLine();
                String[] userDataList = row.split(",");
                String email = userDataList[3];
                if (null != email && !email.isEmpty()) {
                    Optional<Employee> employee = employeeRepo.findEmployeeByEmail(email);
                    if (employee.isPresent()) {
                        mailCount.put(email, 1);
                    } else {
                        mailCount.put(email, 0);
                    }
                }
            }
            Iterator<Map.Entry<String, Integer>> iterator = mailCount.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Integer> pair = iterator.next();
                StringBuilder builder = new StringBuilder(pair.getKey()).append(",")
                        .append(pair.getValue()).append("\n");
                writer.write(builder.toString());
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println("An error occurred.");
        } finally {
            end = System.currentTimeMillis();
        }

        return end - start;
    }

    public Employee findEmployeeById(int id) {
        EmployeeVO employee = employeeProxy.findEmployeeById(id); //findEmployee(id);
        BaseData baseData = employee != null ? employee.getBaseData() : null;
        Employee emp = new Employee();
        emp.setFirstName(baseData != null ? baseData.getFirstName() : null);
        emp.setLastName(baseData != null ? baseData.getLastName() : null);
        emp.setEmail(baseData != null ? baseData.getEmail() : null);
        emp.setSalary(employee.getSalary());
        return emp;
    }


    private EmployeeVO findEmployee(int id) {
        StringBuilder url = new StringBuilder(baseUrl).append("findById/").append(id);
        ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
        //boolean success = HttpStatus.OK.equals(response.getStatusCode());
        if (response.getBody() != null) {
            return gson.fromJson(response.getBody(), EmployeeVO.class);
        }
        return null;
    }

}
