package com.sme.app.repo;

import com.sme.app.entity.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo<T extends EntityBase> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}