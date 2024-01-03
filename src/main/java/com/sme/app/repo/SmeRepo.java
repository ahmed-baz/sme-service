package com.sme.app.repo;


import com.sme.app.entity.Sme;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SmeRepo extends BaseRepo<Sme> {

    Optional<Sme> findByCode(String code);

    List<Sme> findAllByActive(Boolean active);

    @Modifying
    @Transactional
    @Query(value = "UPDATE SME sme SET sme.active = true WHERE sme.id= :id")
    void activateSme(@Param("id") Long id);

}
