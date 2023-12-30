package com.sme.app.vo;

import com.sme.app.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends BaseVo {

    private String name;
    private String email;
    private UserRole role;
    private boolean active;

}


