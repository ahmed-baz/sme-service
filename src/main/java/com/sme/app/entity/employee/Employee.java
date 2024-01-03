package com.sme.app.entity.employee;

import com.sme.app.entity.EntityBase;
import com.sme.app.entity.Sme;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "EMPLOYEE")
@Builder
public class Employee extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "EMPLOYEE_SEQUENCE")
    @SequenceGenerator(name = "EMPLOYEE_SEQUENCE", sequenceName = "EMPLOYEE_SEQUENCE", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    @ManyToOne
    @JoinColumn(name = "SME_Id")
    private Sme sme;

}


