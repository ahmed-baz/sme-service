package com.sme.app.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobVO {

    private String id;
    private String jobTitle;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;

}
