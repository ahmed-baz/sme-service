package com.sme.app.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmeVo extends BaseVo {

    private Long id;
    private String name;
    private String code;
    private String description;
    private Boolean active;

}


