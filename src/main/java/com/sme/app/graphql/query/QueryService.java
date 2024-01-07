package com.sme.app.graphql.query;


import com.sme.app.entity.EntityBase;
import com.sme.app.repo.BaseRepo;
import graphql.kickstart.tools.GraphQLQueryResolver;

public interface QueryService<E extends EntityBase> extends GraphQLQueryResolver {

    Iterable<E> findAll();

    Long count();

    E findById(Long id);

    BaseRepo<E> getRepo();

}
