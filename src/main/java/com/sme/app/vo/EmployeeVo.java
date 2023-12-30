package com.sme.app.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class EmployeeVo extends BaseVo {

    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;

}


