package com.sme.app.vo.employee;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeSalaryVo {

    private String code;
    private int count;
    private BigDecimal sum;

}


