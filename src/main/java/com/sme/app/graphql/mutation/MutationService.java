package com.sme.app.graphql.mutation;


import com.sme.app.entity.EntityBase;
import com.sme.app.repo.BaseRepo;
import graphql.kickstart.tools.GraphQLMutationResolver;


public interface MutationService<E extends EntityBase> extends GraphQLMutationResolver {

    E create(E e);

    E update(Long id, E e);

    boolean delete(Long id);

    BaseRepo<E> getRepo();

}
