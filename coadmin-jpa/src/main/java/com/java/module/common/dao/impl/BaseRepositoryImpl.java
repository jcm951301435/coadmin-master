package com.java.module.common.dao.impl;

import com.java.model.BaseEntity;
import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import com.java.module.common.dao.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * @author: jcm
 * @date: 2020/10/10
 */
@Slf4j
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BaseRepositoryImpl<T extends BaseEntity, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public CommonPage<T> findAll(Specification<T> spec, CommonQueryPageSort pageSort) {
        boolean usePage = true;
        Integer pageNum = pageSort.getPageNum();
        Integer pageSize = pageSort.getPageSize();
        if (pageNum == null || pageSize == null || pageNum < 1 || pageSize < 0) {
            log.info("分页参数{页码: {}, 每页行数: {}}有误, 不使用分页", pageNum, pageSize);
            usePage = false;
        }
        Sort sort = pageSort.generateSort();
        if (usePage) {
            Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
            Page<T> page = this.findAll(spec, pageable);
            return CommonPage.fromPage(page);
        } else {
            List<T> list = this.findAll(spec, sort);
            return CommonPage.all(list);
        }
    }

}
