package com.sample.common.model;

public class PageRequest {
    //查询页，默认值1
    private Integer pageNum;
    //每页大小，默认值10
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
