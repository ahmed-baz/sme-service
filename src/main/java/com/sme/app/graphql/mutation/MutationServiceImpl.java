package com.sme.app.graphql.mutation;


import com.sme.app.entity.EntityBase;
import com.sme.app.exception.AppExceptionResponse;
import com.sme.app.exception.AppErrorKeys;

import java.util.Optional;


public abstract class MutationServiceImpl<E extends EntityBase> implements MutationService<E> {

    @Override
    public E create(E e) {
        return getRepo().save(e);
    }

    @Override
    public E update(Long id, E e) {
        Optional<E> optionalEntity = getRepo().findById(id);
        if (optionalEntity.isPresent()) {
            return getRepo().save(e);
        }
        throw new AppExceptionResponse(AppErrorKeys.NOT_FOUND);
    }

    @Override
    public boolean delete(Long id) {
        Optional<E> entity = getRepo().findById(id);
        if (entity.isPresent()) {
            getRepo().deleteById(id);
            return true;
        }
        throw new AppExceptionResponse(AppErrorKeys.NOT_FOUND);
    }

}
