package com.sme.app.graphql.mutation;


import com.sme.app.entity.Sme;
import com.sme.app.graphql.query.SmeQuery;
import com.sme.app.repo.BaseRepo;
import com.sme.app.repo.SmeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SmeMutation extends MutationServiceImpl<Sme> {

    private final SmeQuery smeQuery;
    private final SmeRepo smeRepo;

    public Sme createSme(String name, String code, String description) {
        Sme sme = Sme.builder().name(name).code(code).description(description).active(true).build();
        return create(sme);
    }

    public Sme updateSme(Long id, String name, String code, String description, Boolean active) {
        Sme oldSme = smeQuery.findSmeById(id);
        Sme sme = Sme.builder().id(id)
                .name(name != null ? name : oldSme.getName())
                .code(code != null ? code : oldSme.getCode())
                .active(active != null ? active : oldSme.getActive())
                .description(description != null ? description : oldSme.getDescription()).build();
        return update(id, sme);
    }

    public boolean deleteSme(Long id) {
        return delete(id);
    }

    @Override
    public BaseRepo<Sme> getRepo() {
        return smeRepo;
    }
}
