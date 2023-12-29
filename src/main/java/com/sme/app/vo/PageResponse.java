package com.sme.app.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResponse<T> {

    private List<T> list;
    private int size;

    public PageResponse(List<T> list) {
        this.list = list;
        this.size = list.size();
    }
}
