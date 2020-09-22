package com.java.module.sys.dao;

import com.java.module.sys.model.SysDepart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Repository
public interface SysDepartRepository extends JpaRepository<SysDepart, Long>, JpaSpecificationExecutor<SysDepart> {

    /**
     * 根据id 批量删除
     *
     * @param ids .
     * @return .
     */
    int deleteByIdIn(List<Long> ids);

}
