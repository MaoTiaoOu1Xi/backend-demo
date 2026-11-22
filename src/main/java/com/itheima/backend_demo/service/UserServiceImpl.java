package com.itheima.backend_demo.service;

import com.itheima.backend_demo.User;
import com.itheima.backend_demo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // 1. 把变量声明为 final
    private final UserMapper userMapper;

    // 2. 用构造方法把 Mapper 传进来（不需要写 @Autowired，Spring 会自动识别）
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
    @CacheEvict(value = "user", key = "#user.id") // 加上这个：更新用户时，删掉 Redis 里的旧缓存
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    @CacheEvict(value = "user", key = "#id") // 加上这个：删除用户时，删掉 Redis 里的旧缓存
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public List<User> findAllWithDept() {
        return userMapper.findAllWithDept();
    }

    @Override
    public List<User> getUserByPage(Integer pageNum, Integer pageSize) {
        // 👇 加上这两行，如果前端传 0 或负数，强制变成 1
        if (pageNum == null || pageNum < 1) pageNum = 1;
        if (pageSize == null || pageSize < 1) pageSize = 2;
        // 公式：起始索引 = (当前页码 - 1) * 每页条数
        int offset = (pageNum - 1) * pageSize;
        return userMapper.findByPage(offset, pageSize);
    }

    @Override
    @Cacheable(value = "user", key = "#id", unless = "#result == null") // 加上 unless，如果查出来是 null 就不存进缓存
    public User getUserById(Integer id) {
        System.out.println("====== 正在查询 MySQL 数据库 ======");
        return userMapper.getUserById(id);
    }
}