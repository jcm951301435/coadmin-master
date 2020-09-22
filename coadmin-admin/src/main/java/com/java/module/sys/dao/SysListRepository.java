package com.java.module.sys.dao;

import com.java.module.sys.model.SysList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Repository
public interface SysListRepository extends JpaRepository<SysList, Long>, JpaSpecificationExecutor<SysList> {

    /**
     * 根据id 批量删除
     *
     * @param ids .
     * @return .
     */
    int deleteByIdIn(List<Long> ids);

    /**
     * 获取导出列表
     *
     * @param params .
     * @return .
     */
//    List<ListExportVO> listExport(@Param("params") ListQueryParamsDTO params);

}
