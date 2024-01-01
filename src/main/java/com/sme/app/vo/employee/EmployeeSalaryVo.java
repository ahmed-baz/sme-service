package com.sme.app.vo.employee;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeSalaryVo {

    private BigDecimal salary;
    private BigDecimal sum;
    private BigDecimal count;

}


