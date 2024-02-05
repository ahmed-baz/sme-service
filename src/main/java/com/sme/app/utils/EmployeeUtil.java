package com.sme.app.utils;


import com.sme.app.vo.employee.EmployeeVo;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class EmployeeUtil {

    private final String[] names = {"Ahmed", "Ali", "Hassan", "Mohamed", "Said", "Saad", "Mostafa", "Ibrahim"};
    private final String[] mail = {"fawry.com", "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "new.markets.com", "stc.com", "stc.pay.com"};
    private final int max = 7;
    private final int min = 0;

    @SneakyThrows
    public EmployeeVo createRandomEmployee(long sleep) {
        EmployeeVo employee = createRandomEmployee();
        TimeUnit.SECONDS.sleep(sleep);
        return employee;
    }

    public EmployeeVo createRandomEmployee() {
        EmployeeVo employee = new EmployeeVo();
        employee.setId(getRandomId());
        employee.setFirstName(names[getRandomIndex()]);
        employee.setLastName(names[getRandomIndex()]);
        String email = employee.getFirstName() + "." +
                employee.getLastName() + "_" +
                getRandomInt() + "@" +
                mail[getRandomIndex()];
        employee.setEmail(email.toLowerCase(Locale.ROOT));
        employee.setSalary(generateRandomSalary(5000, 50000));
        employee.setJoinDate(getRandomDate());
        return employee;
    }

    public BigDecimal generateRandomSalary(int min, int max) {
        int random = min + (int) (Math.random() * ((max - min) + 1));
        return BigDecimal.valueOf((random / 1000) * 1000);
    }

    public List<EmployeeVo> getEmployeeList(int size) {
        List<EmployeeVo> employeeList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            employeeList.add(createRandomEmployee());
        }
        return employeeList;
    }

    public List<EmployeeVo> getEmployeeList(int size, long sleep) {
        List<EmployeeVo> employeeList = new ArrayList<>();
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

    private Integer getRandomInt() {
        Random rand = new Random();
        return rand.nextInt(50000);
    }

    private Date getRandomDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2023, 1, 1).toEpochDay();
        long randomDay = random.nextInt(maxDay - minDay) + minDay;
        LocalDate localDate = LocalDate.ofEpochDay(randomDay);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
