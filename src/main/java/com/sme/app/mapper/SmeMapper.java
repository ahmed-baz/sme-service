package com.sme.app.mapper;

import com.sme.app.entity.Sme;
import com.sme.app.vo.SmeVo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SmeMapper extends BaseMapper<Sme, SmeVo> {
}
