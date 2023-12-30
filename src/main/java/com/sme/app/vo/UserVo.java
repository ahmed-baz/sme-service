package com.sme.app.vo;

import com.sme.app.enums.UserRole;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserVo {

    private Long id;
    private String name;
    private String email;
    private UserRole role;
    private boolean active;

}


