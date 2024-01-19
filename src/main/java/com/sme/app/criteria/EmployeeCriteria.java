package com.sme.app.criteria;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class EmployeeCriteria extends PaginationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salaryFrom;
    private BigDecimal salaryTo;
    private Date joinDateFrom;
    private Date joinDateTo;
    private String smeCode;
}
