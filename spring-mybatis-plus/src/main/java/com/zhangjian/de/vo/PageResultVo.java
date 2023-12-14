package com.zhangjian.de.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResultVo<T> {
    private Integer total;
    private Integer pages;
    private List<T> list;
}
