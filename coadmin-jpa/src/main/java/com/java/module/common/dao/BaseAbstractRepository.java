package com.java.module.common.dao;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * 公共 repository 接口(无需自定义实现, 仅提供方法声明)
 *
 * @author: jcm
 * @date: 2020/10/10
 */
@NoRepositoryBean
public interface BaseAbstractRepository {

    /**
     * 根据id 批量删除
     *
     * @param ids .
     * @return .
     */
    int deleteByIdIn(List<Long> ids);

}
