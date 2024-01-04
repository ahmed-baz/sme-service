package com.sme.app.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseVo implements Serializable {

    private Long id;
    private Long createdBy;
    private Long lastModifiedBy;
    private Date createdAt;
    private Date lastModifiedAt;

}
