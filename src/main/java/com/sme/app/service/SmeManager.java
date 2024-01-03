package com.sme.app.service;


import com.sme.app.mapper.BaseMapper;
import com.sme.app.entity.EntityBase;
import com.sme.app.repo.BaseRepo;
import com.sme.app.vo.BaseVo;

import java.util.List;

public interface SmeManager<E extends EntityBase, VO extends BaseVo> {

    VO add(VO vo);

    VO update(Long id, VO vo);

    void delete(Long id);

    List<VO> findAll();

    VO findById(Long id);

    BaseMapper<E, VO> getMapper();

    BaseRepo<E> getRepo();

}
