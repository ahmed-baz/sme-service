package com.sme.app.repo.employee;


import com.sme.app.entity.employee.Employee;
import com.sme.app.entity.employee.EmployeeView;
import com.sme.app.repo.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends BaseRepo<Employee> {

    Optional<EmployeeView> findEmployeeByEmail(String email);

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "employee_entity_graph")
    List<Employee> findAll();

    List<Employee> findBySmeIsNull();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "employee_entity_graph")
    Page<Employee> findBySmeNameIgnoreCaseContaining(String name, PageRequest pageRequest);

}
