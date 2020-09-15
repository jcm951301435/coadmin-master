package com.java.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.java.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis plus 自动填充
 *
 * @author: jcm
 * @date: 2020/09/09
 */
@Component
public class OperateTimeMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperateTimeMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start fill insert");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getUserDetails().getUsername());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start fill update");
        LOGGER.info(new Date().toString());
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getUserDetails().getUsername(), metaObject);
    }

}
