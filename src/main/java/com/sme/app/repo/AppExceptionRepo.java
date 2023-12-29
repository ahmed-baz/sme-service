package com.sme.app.repo;


import com.sme.app.model.AppException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppExceptionRepo extends JpaRepository<AppException, Long> {

    AppException findByCode(String email);

}
