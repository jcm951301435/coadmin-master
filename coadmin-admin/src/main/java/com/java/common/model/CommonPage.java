package com.java.common.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 分页信息类
 *
 * @author: jcm
 * @date: 2020/05/19
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CommonPage<T> {

    @NonNull
    private Integer pageNum;

    @NonNull
    private Integer pageSize;

    private Integer totalPage;

    private Long total;

    private List<T> list;

    /**
     * 根据 Page 构造 CommonPage
     *
     * @param page
     * @param <E>
     * @return
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
     * @param <E>
     * @return
     */
    public <E> Page<E> getPage() {
        return new Page<>(this.pageNum, this.getPageSize());
    }


}
