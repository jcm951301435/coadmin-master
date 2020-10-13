package com.java.module.sys.dao;

import com.java.module.common.dao.BaseAbstractRepository;
import com.java.module.common.dao.BaseRepository;
import com.java.module.sys.model.SysListItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Repository
public interface SysListItemRepository extends BaseRepository<SysListItem, Long>, BaseAbstractRepository {

    /**
     * 根据 listId 查询所有
     *
     * @param listId .
     * @return .
     */
    List<SysListItem> findByListId(Long listId);

}
