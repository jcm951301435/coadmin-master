package com.java.module.sys.dao;

import com.java.module.sys.model.SysListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Repository
public interface SysListItemRepository extends JpaRepository<SysListItem, Long>, JpaSpecificationExecutor<SysListItem> {

    /**
     * 根据id 批量删除
     *
     * @param ids .
     * @return .
     */
    int deleteByIdIn(List<Long> ids);

}
