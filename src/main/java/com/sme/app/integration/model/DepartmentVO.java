package com.sme.app.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVO {

    private int id;
    private String name;
    private List<EmployeeVO> employees = new ArrayList<>(0);
}
