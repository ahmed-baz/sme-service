package com.sme.app.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryVo {

    private BigDecimal salary;
    private BigDecimal sum;
    private BigDecimal count;

}


