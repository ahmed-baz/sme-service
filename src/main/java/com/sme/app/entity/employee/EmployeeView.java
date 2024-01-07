package com.sme.app.entity.employee;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


public interface EmployeeView {

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    String getEmail();

    @Value("#{target.sme.code}")
    String getSmeCode();

    Date getJoinDate();
}


