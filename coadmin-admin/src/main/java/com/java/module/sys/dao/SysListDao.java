package com.java.module.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.model.SysList;
import com.java.module.sys.service.dto.ListQueryParamsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Repository
public interface SysListDao extends BaseMapper<SysList> {

    /**
     * 获取导出列表
     *
     * @param params .
     * @return .
     */
    List<ListExportVO> listExport(@Param("params") ListQueryParamsDTO params);

}
