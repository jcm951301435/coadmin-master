package com.java.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.java.exception.DuplicateEntityException;
import com.java.module.sys.dao.SysDepartDao;
import com.java.module.sys.mapper.DepartMapper;
import com.java.module.sys.model.SysDepart;
import com.java.module.sys.service.SysDepartService;
import com.java.module.sys.service.dto.DepartQueryParamsDTO;
import com.java.module.sys.service.dto.DepartTreeDTO;
import com.java.util.StringUtils;
import com.java.util.TreeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysDepartServiceImpl implements SysDepartService {

    private final SysDepartDao departDao;

    private final DepartMapper departMapper;

    public SysDepartServiceImpl(SysDepartDao departDao, DepartMapper departMapper) {
        this.departDao = departDao;
        this.departMapper = departMapper;
    }

    @Override
    public List<DepartTreeDTO> treeList(DepartQueryParamsDTO params) {
        List<SysDepart> departs = departList(params);
        List<DepartTreeDTO> departTrees = departMapper.toDepartTree(departs);
        return TreeUtils.getTreeList(departTrees);
    }

    @Override
    public List<DepartTreeDTO> treeListSort(DepartQueryParamsDTO params) {
        List<DepartTreeDTO> departTrees = treeList(params);
        return TreeUtils.sort(departTrees, null);
    }

    /**
     * 部门列表
     * @param params .
     * @return .
     */
    private List<SysDepart> departList(DepartQueryParamsDTO params) {
        QueryWrapper<SysDepart> wrapper = new QueryWrapper<>();
        Date[] createTimeArray = params.getCreateTime();
        if (createTimeArray != null && createTimeArray.length > 0) {
            Date createTimeBegin = createTimeArray[0];
            Date createTimeEnd = null;
            if (createTimeArray.length > 1) {
                createTimeEnd = createTimeArray[1];
            }
            if (createTimeBegin != null) {
                wrapper.ge("create_time", createTimeBegin);
            }
            if (createTimeEnd != null) {
                wrapper.le("create_time", createTimeEnd);
            }
        }
        String blurry = params.getBlurry();
        if (StringUtils.isNotBlank(blurry)) {
            wrapper.like("name", blurry);
        }
        wrapper.orderByAsc("pid").orderByAsc("sort");
        return departDao.selectList(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(SysDepart sysDepart) {
        checkDuplicateBeforeSave(sysDepart, false);
        return departDao.insert(sysDepart);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SysDepart sysDepart) {
        checkDuplicateBeforeSave(sysDepart, true);
        return departDao.updateById(sysDepart);
    }

    /**
     * 保存前检查重复
     *
     * @param sysDepart .
     */
    private void checkDuplicateBeforeSave(SysDepart sysDepart, boolean isUpdate) {
        QueryWrapper<SysDepart> wrapper = new QueryWrapper<>();
        String name = sysDepart.getName();
        Long id = sysDepart.getId();
        Long pid = sysDepart.getPid();
        if (StringUtils.isNotEmpty(name)) {
            wrapper.clear();
            wrapper.eq("name", name);
            wrapper.eq("pid", pid);
            if (isUpdate) {
                wrapper.ne("id", id);
            }
            int count = departDao.selectCount(wrapper);
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此部门名称已存在: %s", name));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(List<Long> ids) {
        return departDao.deleteBatchIds(ids);
    }

}
