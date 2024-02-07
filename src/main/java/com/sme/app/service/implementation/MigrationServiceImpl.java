package com.sme.app.service.implementation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sme.app.entity.employee.Employee;
import com.sme.app.repo.employee.EmployeeRepo;
import com.sme.app.service.MigrationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MigrationServiceImpl implements MigrationService {

    @Value("${employee.data.file.path}")
    private String employeeFilePath;
    @Value("${newEmployee.data.file.path}")
    private String newEmployeeFilePath;
    private final EmployeeRepo employeeRepo;
    private static final String[] EMPLOYEE_HEADER = {"First Name", "Last Name", "Email"};
    private static final String NEW_EMPLOYEE_HEADER = "First Name, Last Name, Email, Salary \n";
    ExecutorService executorService = Executors.newFixedThreadPool(10);


    @Override
    public void createDummyCsv() {
        File file = new File(employeeFilePath);
        List<Employee> all = employeeRepo.findAll();
        createCsv(file, EMPLOYEE_HEADER, all);
    }

    private void createCsv(File file, String[] header, List<Employee> all) {
        try (FileWriter fileWriter = new FileWriter(file); CSVWriter writer = new CSVWriter(fileWriter)) {
            // adding header to csv
            writer.writeNext(header);
            // add data to csv
            all.forEach(employee -> writer.writeNext(new String[]{employee.getFirstName(), employee.getLastName(), employee.getEmail()}));
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public void doMigration() {
        File file = new File(employeeFilePath);
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            Files.writeString(Path.of(newEmployeeFilePath), NEW_EMPLOYEE_HEADER, StandardOpenOption.CREATE);
            while (reader.iterator().hasNext()) {
                String[] line = reader.readNext();
                if (null != line && line.length == 3) {
                    String email = line[2];
                    Optional<Employee> employee = findByEmail(email);
                    if (employee.isPresent()) {
                        Employee emp = employee.get();
                        //processRecord(emp);
                        executorService.submit(() -> processRecord(emp));
                    }
                }

            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void processRecord(Employee emp) {
        sleep();
        StringBuilder record = new StringBuilder(emp.getFirstName())
                .append(",").append(emp.getLastName())
                .append(",").append(emp.getEmail())
                .append(",").append(emp.getSalary()).append("\n");
        try {
            Files.writeString(Path.of(newEmployeeFilePath), record, StandardOpenOption.APPEND);
        } catch (IOException e) {
            log.error(e);
        }
    }


    private Optional<Employee> findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    @SneakyThrows
    private void sleep() {
        Thread.sleep(10);
    }
}
