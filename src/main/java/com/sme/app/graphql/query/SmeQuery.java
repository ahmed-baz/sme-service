package com.sme.app.graphql.query;


import com.sme.app.entity.Sme;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.repo.BaseRepo;
import com.sme.app.repo.SmeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmeQuery extends QueryServiceImpl<Sme> {

    private final SmeRepo SmeRepo;

    public Iterable<Sme> findAllSmes() {
        return findAll();
    }

    public Sme findSmeById(Long id) {
        return findById(id);
    }

    public Sme findSmeByCode(String code) {
        return SmeRepo.findByCode(code).orElseThrow(() -> new AppExceptionResponse(AppErrorKeys.SME_NOT_FOUND));
    }

    public Long countSmes() {
        return count();
    }

    @Override
    public BaseRepo<Sme> getRepo() {
        return SmeRepo;
    }
}
