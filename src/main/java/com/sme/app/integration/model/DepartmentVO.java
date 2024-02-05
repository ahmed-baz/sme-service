package com.sme.app.integration.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentVO {

    private int id;
    private String name;
    private List<EmployeeVO> employees = new ArrayList<>(0);
}
