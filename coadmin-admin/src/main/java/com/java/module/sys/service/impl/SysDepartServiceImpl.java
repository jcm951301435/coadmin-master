package com.java.module.sys.service.impl;

import com.java.exception.DuplicateEntityException;
import com.java.exception.ProjectRunTimeException;
import com.java.module.sys.dao.SysDepartRepository;
import com.java.module.sys.mapper.DepartMapper;
import com.java.module.sys.model.SysDepart;
import com.java.module.sys.service.SysDepartService;
import com.java.module.sys.dto.query.DepartQueryDTO;
import com.java.module.sys.service.dto.DepartTreeDTO;
import com.java.util.StringUtils;
import com.java.util.TreeUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/09/15
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysDepartServiceImpl implements SysDepartService {

    private final SysDepartRepository departRepository;

    private final DepartMapper departMapper;

    public SysDepartServiceImpl(SysDepartRepository departRepository, DepartMapper departMapper) {
        this.departRepository = departRepository;
        this.departMapper = departMapper;
    }

    @Override
    public List<DepartTreeDTO> treeList(DepartQueryDTO params) {
        List<SysDepart> departs = departList(params);
        List<DepartTreeDTO> departTrees = departMapper.toDepartTree(departs);
        return TreeUtils.getTreeList(departTrees);
    }

    @Override
    public List<DepartTreeDTO> treeListSort(DepartQueryDTO params) {
        List<DepartTreeDTO> departTrees = treeList(params);
        return TreeUtils.sort(departTrees, null);
    }

    /**
     * 部门列表
     *
     * @param params .
     * @return .
     */
    private List<SysDepart> departList(DepartQueryDTO params) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "sort"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "name"));
        return departRepository.findAll((Specification<SysDepart>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (params != null) {
                Date[] createTimeArray = params.getCreateTime();
                String blurry = params.getBlurry();
                if (createTimeArray != null && createTimeArray.length > 0) {
                    Date createTimeBegin = createTimeArray[0];
                    Date createTimeEnd = null;
                    if (createTimeArray.length > 1) {
                        createTimeEnd = createTimeArray[1];
                    }
                    if (createTimeBegin != null) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime"),
                                createTimeBegin));
                    }
                    if (createTimeEnd != null) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"),
                                createTimeEnd));
                    }
                }
                if (StringUtils.isNotBlank(blurry)) {
                    blurry = "%" + blurry + "%";
                    predicates.add(criteriaBuilder.like(root.get("name"), blurry));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, Sort.by(orders));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysDepart sysDepart) {
        checkDuplicateBeforeSave(sysDepart, false);
        departRepository.save(sysDepart);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDepart sysDepart) {
        if (sysDepart.getId().equals(sysDepart.getPid())) {
            throw new ProjectRunTimeException("上级部门不能为自己！");
        }
        checkDuplicateBeforeSave(sysDepart, true);
        departRepository.save(sysDepart);
    }

    /**
     * 保存前检查重复
     *
     * @param sysDepart .
     */
    private void checkDuplicateBeforeSave(SysDepart sysDepart, boolean isUpdate) {
        String name = sysDepart.getName();
        Long id = sysDepart.getId();
        Long pid = sysDepart.getPid();
        if (StringUtils.isNotEmpty(name)) {
            long count = departRepository.count((Specification<SysDepart>) (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.like(root.get("name"), name));
                predicates.add(criteriaBuilder.equal(root.get("pid"), pid));
                if (isUpdate) {
                    predicates.add(criteriaBuilder.notEqual(root.get("id"), id));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            });
            if (count > 0) {
                throw new DuplicateEntityException(String.format("此部门名称已存在: %s", name));
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> ids) {
        departRepository.deleteByIdIn(ids);
    }

}
