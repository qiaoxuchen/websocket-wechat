package com.qiao.users.service;

import com.qiao.users.bean.User;

public interface UserService {

    public User select(User loginUser);

    public int insert(User loginUser);
}
