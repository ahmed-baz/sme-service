package com.sme.app.vo.employee;

import com.sme.app.vo.AddressVo;
import com.sme.app.vo.BaseVo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class EmployeeVo extends BaseVo {

    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    private List<AddressVo> address;

}


