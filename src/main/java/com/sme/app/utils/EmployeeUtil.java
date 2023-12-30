package com.sme.app.utils;


import com.sme.app.entity.Employee;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class EmployeeUtil {

    private String names[] = {"Ahmed", "Ali", "Hassan", "Mohamed", "Said", "Saad", "Mostafa", "Ibrahim"};
    private String mail[] = {"fawry.com", "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "new.markets.com", "stc.com", "stc.pay.com"};
    private BigDecimal salary[] = {BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), BigDecimal.valueOf(7000), BigDecimal.valueOf(8000), BigDecimal.valueOf(9000), BigDecimal.valueOf(10000), BigDecimal.valueOf(11000), BigDecimal.valueOf(12000)};
    private int max = 7;
    private int min = 0;

    @SneakyThrows
    public Employee createRandomEmployee(long sleep) {
        Employee employee = createRandomEmployee();
        TimeUnit.SECONDS.sleep(sleep);
        return employee;
    }

    public Employee createRandomEmployee() {
        Employee employee = new Employee();
        employee.setId(getRandomId());
        employee.setFirstName(names[getRandomIndex()]);
        employee.setLastName(names[getRandomIndex()]);
        StringBuilder email = new StringBuilder(employee.getFirstName()).append(".")
                .append(employee.getLastName())
                .append("@").append(mail[getRandomIndex()]);
        employee.setEmail(email.toString().toLowerCase(Locale.ROOT));
        employee.setSalary(salary[getRandomIndex()]);
        return employee;
    }

    public List<Employee> getEmployeeList(int size) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            employeeList.add(createRandomEmployee());
        }
        return employeeList;
    }

    public List<Employee> getEmployeeList(int size, long sleep) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            employeeList.add(createRandomEmployee(sleep));
        }
        return employeeList;
    }

    private int getRandomIndex() {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private Long getRandomId() {
        Random rand = new Random();
        return rand.nextLong();
    }

}
