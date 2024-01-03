package com.sme.app.entity.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Immutable
public class EmployeeSalary {

    @Id
    private BigDecimal salary;
    private BigDecimal sum;
    private BigDecimal count;

}


