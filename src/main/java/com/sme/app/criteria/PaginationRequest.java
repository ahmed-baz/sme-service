package com.sme.app.criteria;


import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Data
public class PaginationRequest {

    @PositiveOrZero
    private Integer pageNumber;

    @Positive
    private Integer pageSize;

}
