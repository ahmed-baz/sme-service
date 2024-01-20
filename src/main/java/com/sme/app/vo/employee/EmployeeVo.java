package com.sme.app.vo.employee;

import com.sme.app.vo.AddressVo;
import com.sme.app.vo.BaseVo;
import com.sme.app.vo.SmeVo;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.sme.app.exception.AppErrorKeys.*;


@Getter
@Setter
public class EmployeeVo extends BaseVo {

    private String fullName;
    @NotEmpty(message = FIRST_NAME_IS_REQUIRED)
    private String firstName;
    @NotEmpty(message = LAST_NAME_IS_REQUIRED)
    private String lastName;
    @NotEmpty(message = EMAIL_IS_REQUIRED)
    private String email;
    @NotNull(message = SALARY_IS_REQUIRED)
    private BigDecimal salary;
    private List<AddressVo> address;
    private Date joinDate;
    private SmeVo sme;
    private String smeCode;

}


