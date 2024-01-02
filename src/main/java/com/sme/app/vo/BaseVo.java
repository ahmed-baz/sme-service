package com.sme.app.vo;


import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseVo {

    private Long id;
    private Long createdBy;
    private Long lastModifiedBy;
    private Date createdAt;
    private Date lastModifiedAt;

}
