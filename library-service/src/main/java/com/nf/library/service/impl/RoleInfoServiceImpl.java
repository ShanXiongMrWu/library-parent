package com.nf.library.service.impl;


import com.nf.library.dao.RoleInfoDao;
import com.nf.library.entity.RoleInfo;
import com.nf.library.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务实现类
 * @author Sam
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Autowired
    private RoleInfoDao roleInfoDao;

    @Override
    public List<RoleInfo> getRoleByUsername(String username) {
        return roleInfoDao.getRoleByUsername(username);
    }
}