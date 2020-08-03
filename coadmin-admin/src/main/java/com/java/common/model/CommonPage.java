package com.java.common.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 分页信息类
 *
 * @author: jcm
 * @date: 2020/05/19
 */
public class CommonPage<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Integer totalPage;

    private Long total;

    private List<T> list;

    /**
     * 根据 Page 构造 CommonPage
     *
     * @param page .
     * @param <E>  .
     * @return .
     */
    public static <E> CommonPage<E> fromPage(Page<E> page) {
        CommonPage<E> commonPage = new CommonPage<>();
        commonPage.setPageNum(((Long) page.getCurrent()).intValue());
        commonPage.setPageSize(((Long) page.getSize()).intValue());
        commonPage.setTotalPage(((Long) page.getPages()).intValue());
        commonPage.setTotal(page.getTotal());
        commonPage.setList(page.getRecords());
        return commonPage;
    }

    /**
     * 根据 CommonPage 构造 Page
     * 主要用于调用 dao 方法
     *
     * @param <E> .
     * @return .
     */
    public <E> Page<E> getPage() {
        return new Page<>(this.pageNum, this.getPageSize());
    }


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public CommonPage() {
    }

    public CommonPage(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
