package com.sme.app.mapper;


import com.sme.app.entity.EntityBase;
import com.sme.app.vo.BaseVo;
import org.mapstruct.MappingTarget;

import java.util.List;


public interface BaseMapper<E extends EntityBase, VO extends BaseVo> {

    List<VO> entityListToVoList(List<E> entityList);

    VO entityToVo(E e);

    E voToEntity(VO VO);

    void updateEntityFromVo(VO VO, @MappingTarget E e);

    List<E> voListToEntityList(List<VO> list);

}
