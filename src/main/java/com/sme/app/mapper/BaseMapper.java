package com.sme.app.mapper;


import com.sme.app.vo.payload.PageResponse;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BaseMapper<E, VO> {

    List<VO> entityListToVoList(List<E> entityList);

    VO entityToVo(E e);

    E voToEntity(VO VO);

    void updateEntityFromVo(VO VO, @MappingTarget E e);

    List<E> voListToEntityList(List<VO> list);

    default PageResponse<VO> preparePageResponse(Page<E> page) {
        PageResponse<VO> response = new PageResponse<>();
        response.setList(entityListToVoList(page.getContent()));
        response.setTotalElements(page.getTotalElements());
        response.setPageSize(page.getPageable().getPageSize());
        response.setPageNumber(page.getPageable().getPageNumber());
        response.setTotalPages(page.getTotalPages());
        return response;
    }

}
