package com.sme.app.integration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmeEmployeeVO {

    private Long id;
    private BaseData baseData;
    private DepartmentVO department;
    private JobVO job;
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date joinDate;

}
