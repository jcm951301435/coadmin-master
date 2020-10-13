package com.java.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java.serializer.LongJsonSerializer;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页信息类
 *
 * @author: jcm
 * @date: 2020/05/19
 */
@Data
public class CommonPage<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Integer totalPage;

    @JsonSerialize(using = LongJsonSerializer.class)
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
        commonPage.setPageNum(page.getNumber());
        commonPage.setPageSize(page.getSize());
        commonPage.setTotalPage(page.getTotalPages());
        commonPage.setTotal(page.getTotalElements());
        commonPage.setList(page.getContent());
        return commonPage;
    }

    /**
     * 所有数据
     * @param list .
     * @param <E> .
     * @return .
     */
    public static <E> CommonPage<E> all(List<E> list) {
        CommonPage<E> commonPage = new CommonPage<>();
        commonPage.setPageNum(1);
        commonPage.setPageSize(list.size());
        commonPage.setTotalPage(1);
        commonPage.setTotal((long) list.size());
        commonPage.setList(list);
        return commonPage;
    }

}
