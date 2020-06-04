package com.java.module.service.sys.impl;

import com.java.common.model.CommonPage;
import com.java.module.sys.model.SysUser;
import com.java.module.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@Slf4j
class SysUserServiceImplTest {

    @Autowired
    private SysUserService userService;

    @Test
    void listAll() {
        CommonPage<SysUser> userPage = userService.listAll(new CommonPage<SysUser>(1, 1));
        log.info(new ResponseEntity<>(userPage, HttpStatus.OK).toString());
    }

}