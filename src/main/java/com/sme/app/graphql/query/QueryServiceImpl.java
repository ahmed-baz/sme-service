package com.sme.app.graphql.query;


import com.sme.app.entity.EntityBase;
import com.sme.app.exception.AppErrorKeys;
import com.sme.app.exception.AppExceptionResponse;

public abstract class QueryServiceImpl<E extends EntityBase> implements QueryService<E> {

    @Override
    public Iterable<E> findAll() {
        return getRepo().findAll();
    }

    @Override
    public Long count() {
        return getRepo().count();
    }

    @Override
    public E findById(Long id) {
        return getRepo().findById(id).orElseThrow(() -> new AppExceptionResponse(AppErrorKeys.NOT_FOUND));
    }
}
