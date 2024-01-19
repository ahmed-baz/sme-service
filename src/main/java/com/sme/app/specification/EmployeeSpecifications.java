package com.sme.app.specification;

import com.sme.app.criteria.EmployeeCriteria;
import com.sme.app.entity.Sme;
import com.sme.app.entity.employee.Employee;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Date;

@UtilityClass
public class EmployeeSpecifications {

    public Specification<Employee> createEmployeeSpec(EmployeeCriteria criteria) {
        Specification<Employee> spec = Specification.where(null);
        Specification<Employee> firstNameSpec = AppSpecifications.propertyLike("firstName", criteria.getFirstName());
        Specification<Employee> lastNameSpec = AppSpecifications.propertyLike("lastName", criteria.getLastName());
        Specification<Employee> emailSpec = AppSpecifications.propertyLike("email", criteria.getEmail());
        spec = spec
                .and(firstNameSpec)
                .and(lastNameSpec)
                .and(emailSpec)
                .and(hasSalaryGreaterThan(criteria.getSalaryFrom()))
                .and(hasSalaryLessThan(criteria.getSalaryTo()))
                .and(hasJoinDateGreaterThan(criteria.getJoinDateFrom()))
                .and(hasJoinDateLessThan(criteria.getJoinDateTo()))
                .and(smeCodeIs(criteria.getSmeCode()));
        return spec;
    }

    private static Specification<Employee> hasSalaryGreaterThan(BigDecimal from) {
        return (root, query, criteriaBuilder) -> {
            if (from == null) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), from);
            }
        };
    }

    private static Specification<Employee> hasSalaryLessThan(BigDecimal to) {
        return (root, query, criteriaBuilder) -> {
            if (to == null) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("salary"), to);
            }
        };
    }

    private static Specification<Employee> hasJoinDateGreaterThan(Date from) {
        return (root, query, criteriaBuilder) -> {
            if (from == null) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("joinDate"), from);
            }
        };
    }

    private static Specification<Employee> hasJoinDateLessThan(Date from) {
        return (root, query, criteriaBuilder) -> {
            if (from == null) {
                return criteriaBuilder.conjunction();
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("joinDate"), from);
            }
        };
    }

    private static Specification<Employee> smeCodeIs(String smeCode) {
        return (root, query, criteriaBuilder) -> {
            if (smeCode == null || smeCode.isEmpty()) {
                return criteriaBuilder.conjunction();
            } else {
                Join<Employee, Sme> employeeSmeJoin = root.join("sme", JoinType.INNER);
                return criteriaBuilder.equal(employeeSmeJoin.get("code"), smeCode);
            }
        };
    }

}
