package com.java.module.common.dao;

import com.java.model.BaseEntity;
import com.java.model.CommonPage;
import com.java.model.CommonQueryPageSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 公共 repository 接口(需自定义实现)
 *
 * @author: jcm
 * @date: 2020/10/10
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * 分页查询 若无分页参数 则查询所有
     *
     * @param spec     .
     * @param pageSort .
     * @return .
     */
    CommonPage<T> findAll(Specification<T> spec, CommonQueryPageSort pageSort);

}
