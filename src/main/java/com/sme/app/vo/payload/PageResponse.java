package com.sme.app.vo.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> list;
    private Long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;

    public PageResponse(List<T> list) {
        this.list = list;
        this.totalElements = (long) list.size();
    }
}
