package com.java.config;

import com.java.model.BaseEntity;
import com.java.module.common.dao.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 自定义 repository 工厂生产类
 *
 * @param <R>  Repository
 * @param <T>  BaseEntity
 * @param <ID> ID
 * @author: jcm
 * @date: 2020/10/10
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, ID>, T extends BaseEntity, ID extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, ID> {

    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory<T, ID>(entityManager);
    }

    /**
     * 自定义 repository 工厂
     */
    private static class BaseRepositoryFactory<T extends BaseEntity, ID extends Serializable>
            extends JpaRepositoryFactory {

        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(
                RepositoryInformation information, EntityManager entityManager) {
            return new BaseRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }

}
