package com.java.module.sys.dao;

import com.java.module.common.dao.BaseAbstractRepository;
import com.java.module.common.dao.BaseRepository;
import com.java.module.sys.model.SysList;
import org.springframework.stereotype.Repository;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Repository
public interface SysListRepository extends BaseRepository<SysList, Long>, BaseAbstractRepository {

    /**
     * 根据 type 获取 SysList
     *
     * @param type .
     * @return .
     */
    SysList findByType(String type);

}
