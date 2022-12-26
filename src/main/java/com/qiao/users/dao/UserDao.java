package com.qiao.users.dao;


import com.qiao.users.bean.User;

public interface UserDao {
    User selectUser(User loginUser);

    int insert(User loginUser);

}
