package com.sme.app.graphql;

import com.sme.app.entity.Sme;
import com.sme.app.entity.employee.Employee;
import com.sme.app.repo.SmeRepo;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeResolver implements GraphQLResolver<Employee> {

    private final SmeRepo smeRepo;

    public Sme getSme(Employee employee) {
        Optional<Sme> sme = smeRepo.findById(employee.getSme().getId());
        if (sme.isPresent()) {
            return sme.get();
        }
        return null;
    }

}
