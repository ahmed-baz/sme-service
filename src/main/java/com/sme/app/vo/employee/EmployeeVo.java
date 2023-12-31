package com.sme.app.vo.employee;

import com.sme.app.vo.BaseVo;
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


