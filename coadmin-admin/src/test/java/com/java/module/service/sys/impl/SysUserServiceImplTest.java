package com.java.module.service.sys.impl;

import com.java.common.model.CommonPage;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SysUserServiceImplTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImplTest.class);

    @Autowired
    private SysUserService userService;

    @Test
    void listAll() {
        CommonPage<SysUser> userPage = userService.listAll(new CommonPage<SysUser>(1, 1));
        LOGGER.info(new ResponseEntity<>(userPage, HttpStatus.OK).toString());
    }

}