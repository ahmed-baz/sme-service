package com.demo.user.repo;


import com.demo.user.model.AppException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppExceptionRepo extends JpaRepository<AppException, Long> {

    AppException findByCode(String email);

}
