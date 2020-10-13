package com.java.module.sys.service.impl;

import com.java.exception.DuplicateEntityException;
import com.java.exception.ProjectRunTimeException;
import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import com.java.module.sys.action.vo.ListExportVO;
import com.java.module.sys.dao.SysListRepository;
import com.java.module.sys.dto.ListDTO;
import com.java.module.sys.dto.query.ListQueryDTO;
import com.java.module.sys.mapper.ListMapper;
import com.java.module.sys.model.SysList;
import com.java.module.sys.model.SysListItem;
import com.java.module.sys.service.SysListItemService;
import com.java.module.sys.service.SysListService;
import com.java.module.sys.service.dto.SysListDTO;
import com.java.util.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysListServiceImpl implements SysListService {

    private final SysListRepository listRepository;

    private final SysListItemService itemService;

    private final ListMapper listMapper;

    public SysListServiceImpl(SysListRepository listRepository, SysListItemService itemService,
                              ListMapper listMapper) {
        this.listRepository = listRepository;
        this.itemService = itemService;
        this.listMapper = listMapper;
    }

    @Override
    public CommonPage<ListDTO> page(ListQueryDTO params, CommonQueryPageSort pageSort) {
        CommonPage<SysList> listPage = listRepository.findAll(params.toSpecification(), pageSort);
        return listMapper.toDTO(listPage);
    }

    @Override
    public SysListDTO findOneByType(String type) {
        SysList list = listRepository.findByType(type);
        if (list == null) {
            return null;
        }
        Long id = list.getId();
        List<SysListItem> itemList = itemService.findListByListId(id);
//        return listMapper.toListDTO(list, itemList);
        return null;
    }

    @Override
    public List<ListExportVO> listExport(ListQueryDTO params) {
        List<Sort.Order> orders = new ArrayList<>();
        List<SysList> lists = listRepository.findAll(params.toSpecification(), Sort.by(orders));
//        return listDao.listExport(params);
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysList sysList) {
        checkDuplicateBeforeSave(sysList, false);
        listRepository.save(sysList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysList sysList) {
        checkDuplicateBeforeSave(sysList, true);
        listRepository.save(sysList);
    }

    /**
     * 保存前检查
     *
     * @param sysList  .
     * @param isUpdate .
     */
    private void checkDuplicateBeforeSave(SysList sysList, boolean isUpdate) {
        String type = sysList.getType();
        String value = sysList.getValue();
        Long id = sysList.getId();
        if (StringUtils.isNotEmpty(type)) {
            long count = listRepository.count((Specification<SysList>) (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("type"), type));
                if (isUpdate) {
                    predicates.add(cb.notEqual(root.get("id"), id));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            });
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此类型已存在: %s", type));
            }
        }
        if (StringUtils.isNotEmpty(value)) {
            long count = listRepository.count((Specification<SysList>) (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("value"), value));
                if (isUpdate) {
                    predicates.add(cb.notEqual(root.get("id"), id));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            });
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此类型已存在: %s", value));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        int itemCount = itemService.countByListIds(ids);
        if (itemCount > 0) {
            throw new ProjectRunTimeException("存在列选明细未删除，请先删除对应的明细！");
        }
        listRepository.deleteByIdIn(ids);
    }

}
