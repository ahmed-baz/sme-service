package com.sme.app.vo;

import com.sme.app.enums.UserRole;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo extends BaseVo {

    private String name;
    private String email;
    private UserRole role;
    private boolean active;

}


