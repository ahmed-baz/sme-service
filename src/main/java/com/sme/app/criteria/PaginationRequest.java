package com.sme.app.criteria;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import static com.sme.app.exception.AppErrorKeys.*;


@Data
public class PaginationRequest {

    @PositiveOrZero(message = INVALID_PAGE_NUMBER)
    @NotNull(message = PAGE_NUMBER_IS_REQUIRED)
    private Integer pageNumber;

    @Positive(message = INVALID_PAGE_SIZE)
    @NotNull(message = PAGE_SIZE_IS_REQUIRED)
    private Integer pageSize;

}
