package com.itheima.backend_demo.service;

import com.itheima.backend_demo.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
    List<User> findAllWithDept();
    List<User> getUserByPage(Integer pageNum, Integer pageSize);
}