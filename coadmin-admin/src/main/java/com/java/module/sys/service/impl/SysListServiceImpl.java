package com.java.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.exception.DuplicateEntityException;
import com.java.model.CommonPage;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.dao.SysListDao;
import com.java.module.sys.mapper.ListMapper;
import com.java.module.sys.model.SysList;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.SysListItemService;
import com.java.module.sys.service.SysListService;
import com.java.module.sys.service.dto.ListQueryParamsDTO;
import com.java.module.sys.service.dto.SysListDTO;
import com.java.util.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysListServiceImpl implements SysListService {

    private final SysListDao listDao;

    private final SysListItemService itemService;

    private final ListMapper listMapper;

    public SysListServiceImpl(SysListDao listDao, SysListItemService itemService, ListMapper listMapper) {
        this.listDao = listDao;
        this.itemService = itemService;
        this.listMapper = listMapper;
    }

    @Override
    public CommonPage<SysList> page(ListQueryParamsDTO params, CommonPage<SysList> commonPage) {
        String blurry = params.getBlurry();
        QueryWrapper<SysList> listWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(blurry)) {
            listWrapper.like("type", blurry);
            listWrapper.or().like("value", blurry);
        }
        Page<SysList> page = null;
        if (commonPage != null) {
            page = commonPage.getPage();
        }
        page = listDao.selectPage(page, listWrapper);
        return CommonPage.fromPage(page);
    }

    @Override
    @Cacheable(value = "SysListDTO", key = "#type")
    public SysListDTO findOneByType(String type) {
        QueryWrapper<SysList> listWrapper = new QueryWrapper<>();
        listWrapper.eq("type", type);
        SysList list = listDao.selectOne(listWrapper);
        if (list == null) {
            return null;
        }
        Long id = list.getId();
        List<SysListItem> itemList = itemService.findListByListId(id);
        return listMapper.toListDTO(list, itemList);
    }

    @Override
    public List<ListExportVO> listExport(ListQueryParamsDTO params) {
        return listDao.listExport(params);
    }

    @Override
    public List<SysList> list(ListQueryParamsDTO params) {
        String blurry = params.getBlurry();
        QueryWrapper<SysList> listWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(blurry)) {
            listWrapper.like("type", blurry);
            listWrapper.or().like("value", blurry);
        }
        return listDao.selectList(listWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "SysListDTO", key = "#sysList.type", condition = "#result > 0")
    public int create(SysList sysList) {
        checkDuplicateBeforeSave(sysList, false);
        return listDao.insert(sysList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "SysListDTO", key = "#sysList.type", condition = "#result > 0")
    public int update(SysList sysList) {
        checkDuplicateBeforeSave(sysList, true);
        return listDao.updateById(sysList);
    }

    /**
     * 保存前检查
     *
     * @param sysList  .
     * @param isUpdate .
     */
    private void checkDuplicateBeforeSave(SysList sysList, boolean isUpdate) {
        QueryWrapper<SysList> listWrapper = new QueryWrapper<>();
        String type = sysList.getType();
        String value = sysList.getValue();
        Long id = sysList.getId();
        if (StringUtils.isNotEmpty(type)) {
            listWrapper.eq("type", type);
            if (isUpdate) {
                listWrapper.ne("id", id);
            }
            int count = listDao.selectCount(listWrapper);
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此类型已存在: %s", type));
            }
        }
        if (StringUtils.isNotEmpty(value)) {
            listWrapper.clear();
            listWrapper.eq("value", value);
            if (isUpdate) {
                listWrapper.ne("id", id);
            }
            int count = listDao.selectCount(listWrapper);
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此类型已存在: %s", value));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "SysListDTO", allEntries = true, condition = "#result > 0")
    public int delete(List<Long> ids) {
        return listDao.deleteBatchIds(ids);
    }

}
