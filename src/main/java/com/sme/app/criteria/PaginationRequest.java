package com.sme.app.criteria;


import lombok.Data;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
public class PaginationRequest {

    @PositiveOrZero
    private Integer index;

    @Positive
    private Integer size;

}
