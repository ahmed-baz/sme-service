package com.sme.app.mapper;


import com.sme.app.entity.User;
import com.sme.app.vo.UserVo;
import org.mapstruct.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User, UserVo> {

}
