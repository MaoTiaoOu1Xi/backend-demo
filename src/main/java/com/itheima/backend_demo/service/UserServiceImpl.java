package com.itheima.backend_demo.service;

import com.itheima.backend_demo.User;
import com.itheima.backend_demo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public void addUser(User user) {
        if(user.getName()==null||user.getName().trim().isEmpty()){
            throw new RuntimeException("新增失败：用户姓名不能为空！");
        }
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public List<User> findAllWithDept() {
        return userMapper.findAllWithDept();
    }

    @Override
    public List<User> getUserByPage(Integer pageNum, Integer pageSize) {
        // 公式：起始索引 = (当前页码 - 1) * 每页条数
        int offset = (pageNum - 1) * pageSize;
        return userMapper.findByPage(offset, pageSize);
    }

    @Override
    @Cacheable(value = "user", key = "#id") // <--- 核心！这就是我们加的缓存
    public User getUserById(Integer id) {
        System.out.println("====== 正在查询 MySQL 数据库 ======");
        return userMapper.getUserById(id);
    }
}