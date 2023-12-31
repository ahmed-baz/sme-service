package com.sme.app.mapper;


import org.mapstruct.MappingTarget;

import java.util.List;


public interface BaseMapper<E, VO> {

    List<VO> entityListToVoList(List<E> entityList);

    VO entityToVo(E e);

    E voToEntity(VO VO);

    void updateEntityFromVo(VO VO, @MappingTarget E e);

    List<E> voListToEntityList(List<VO> list);

}
