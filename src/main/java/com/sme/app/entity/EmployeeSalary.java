package com.sme.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EMPLOYEE_SALARY")
@Immutable
public class EmployeeSalary {

    @Id
    private BigDecimal salary;
    private BigDecimal sum;
    private BigDecimal count;

}


