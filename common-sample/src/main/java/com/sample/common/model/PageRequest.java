package com.sample.common.model;

public class PageRequest {
    private Integer pageNum;
    private Integer size;

    public PageRequest(Integer pageNum, Integer size) {
        this.pageNum = pageNum;
        this.size = size;
    }

    public Integer getPageNum() {
        return pageNum==null?1:pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getSize() {
        return size==null?10:size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
