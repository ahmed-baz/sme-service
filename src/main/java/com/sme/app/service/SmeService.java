package com.sme.app.service;

import com.sme.app.vo.SmeVo;

import java.util.List;

public interface SmeService {

    SmeVo findSmeById(Long id);

    SmeVo findSmeByCode(String code);

    List<SmeVo> findAllSmes();

    SmeVo addSme(SmeVo smeVo);

    SmeVo updateSme(Long id, SmeVo smeVo);

    Long activateSme(Long id);

    Long deleteSme(Long id);

}
