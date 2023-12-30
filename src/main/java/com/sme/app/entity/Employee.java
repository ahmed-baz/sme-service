package com.sme.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EMPLOYEE")
public class Employee extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EMPLOYEE_SEQUENCE")
    @SequenceGenerator(name = "EMPLOYEE_SEQUENCE", sequenceName = "EMPLOYEE_SEQUENCE", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;

}


