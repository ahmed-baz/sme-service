package com.sme.app.specification;

import com.sme.app.criteria.EmployeeCriteria;
import com.sme.app.entity.employee.Employee;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class EmployeeSpecifications {

    public Specification<Employee> createEmployeeSpec(EmployeeCriteria criteria) {
        Specification<Employee> firstName = AppSpecifications.propertyLike("firstName", criteria.getFirstName());
        Specification<Employee> lastName = AppSpecifications.propertyLike("lastName", criteria.getLastName());
        Specification<Employee> emailName = AppSpecifications.propertyLike("email", criteria.getEmail());
        return Specification.where(firstName).and(lastName).and(emailName);
    }

}
