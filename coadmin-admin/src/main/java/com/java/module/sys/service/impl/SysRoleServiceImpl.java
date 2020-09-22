package com.java.module.sys.service.impl;

import com.java.model.CommonPage;
import com.java.module.sys.dao.SysRoleRepository;
import com.java.module.sys.model.SysRole;
import com.java.module.sys.service.SysRoleService;
import com.java.module.sys.service.dto.RoleQueryParamsDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色业务接口实现
 *
 * @author: jcm
 * @date: 2020/06/14
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleRepository roleRepository;

    public SysRoleServiceImpl(SysRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public CommonPage<SysRole> page(RoleQueryParamsDTO params, CommonPage<SysRole> commonPage) {
//        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
//        List<RoleDTO> roleDTOS = roleDao.listRole(params);
//        Page<SysRole> page = commonPage.getPage();
//        page = roleDao.selectPage(page, wrapper);
//        return CommonPage.fromPage(page);
        return null;
    }

    @Override
    public List<SysRole> list(RoleQueryParamsDTO params) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(SysRole sysRole) {
        checkDuplicateBeforeSave(sysRole, false);
        roleRepository.save(sysRole);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SysRole sysRole) {
        checkDuplicateBeforeSave(sysRole, true);
        roleRepository.save(sysRole);
        return 0;
    }

    /**
     * 保存前检查重复
     *
     * @param sysRole  .
     * @param isUpdate .
     */
    private void checkDuplicateBeforeSave(SysRole sysRole, boolean isUpdate) {
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(List<Long> ids) {
        return roleRepository.deleteByIdIn(ids);
    }

}
