package com.sme.app.service.implementation;


import com.sme.app.criteria.PaginationRequest;
import com.sme.app.exception.ResourceNotFoundException;
import com.sme.app.model.EntityBase;
import com.sme.app.repo.BaseRepo;
import com.sme.app.service.SmeManager;
import com.sme.app.vo.BaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
@Getter
@Log4j2
public abstract class SmeManagerImpl<E extends EntityBase, VO extends BaseVo, R extends PaginationRequest> implements SmeManager<E, VO> {

    @Override
    public VO add(VO vo) {
        doValidateBeforeMapping(vo);
        E entity = getMapper().voToEntity(vo);
        BaseRepo<E> repo = getRepo();
        doValidateEntityBeforeCreate(vo, entity);
        doPrepareEntity(vo, entity);
        E savedEntity = repo.save(entity);
        doAfterCreate(vo, entity);
        return getMapper().entityToVo(savedEntity);
    }


    @Override
    public VO update(VO vo, Long id) {
        E entity = loadById(id);
        doValidateBeforeMapping(vo);
        getMapper().updateEntityFromVo(vo, entity);
        doBeforeUpdate(vo, entity);
        doValidateEntityBeforeUpdate(vo, entity);
        E savedEntity = doUpdate(entity);
        doAfterUpdate(vo, entity);
        return getMapper().entityToVo(savedEntity);
    }

    private E doUpdate(E entity) {
        return getRepo().save(entity);
    }

    @Override
    public void delete(Long id) {
        E e = loadById(id);
        if (null != e) {
            doBeforeDelete(id);
            getRepo().deleteById(id);
            doAfterDelete(id);
        }
    }

    @Override
    public List<VO> findAll() {
        List<E> all = getRepo().findAll(Sort.by("createdAt").ascending());
        return getMapper().entityListToVoList(all);
    }


    protected PageRequest preparePageable(R request) {
        return PageRequest.of(request.getIndex(), request.getSize());
    }


    @Override
    public VO findById(Long id) {
        E e = loadById(id);
        return getMapper().entityToVo(e);
    }

    private E loadById(Long id) {
        Optional<E> optionalE = getRepo().findById(id);
        if (optionalE.isPresent()) {
            return optionalE.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    protected void doValidateBeforeMapping(VO dto) {
    }

    protected void doValidateEntityBeforeCreate(VO dto, E entity) {
    }

    protected void doValidateEntityBeforeUpdate(VO dto, E entity) {
    }

    protected void doPrepareEntity(VO dto, E entity) {
    }

    protected void doAfterCreate(VO dto, E entity) {
    }

    protected void doBeforeUpdate(VO dto, E entity) {
    }

    protected void doAfterUpdate(VO dto, E entity) {
    }

    protected void doBeforeDelete(Long id) {
    }

    protected void doAfterDelete(Long id) {
    }
}
