package com.sme.app.vo;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmeVo extends BaseVo {

    private Long id;
    private String name;
    private String code;
    private String description;
    private Boolean active;

}


