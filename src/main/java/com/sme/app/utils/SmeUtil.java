package com.sme.app.utils;

import com.sme.app.entity.Sme;
import com.sme.app.vo.SmeVo;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;

@UtilityClass
public class SmeUtil {

    public Sme anySme(List<SmeVo> smes) {
        Random random = new Random();
        int index = random.nextInt(smes.size());
        return Sme.builder().id(smes.get(index).getId()).build();
    }

}
