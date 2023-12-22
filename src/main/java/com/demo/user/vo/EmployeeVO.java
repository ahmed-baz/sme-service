package com.demo.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {

    private int id;
    private BaseData baseData;
    private DepartmentVO department;
    private JobVO job;
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date joinDate;
    private OfficialIdVO officialId;


}
