package com.qiao.users.service.impl;

import com.qiao.users.bean.User;
import com.qiao.users.service.UserService;
import com.qiao.users.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userdao;

    @Override
    public User select(User loginUser) {
        return userdao.selectUser(loginUser);
    }

    @Override
    public int insert(User loginUser) {
        return userdao.insert(loginUser);
    }
}
