package com.itheima.backend_demo;

import org.apache.ibatis.annotations.*;
import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(name, age) VALUES (#{name}, #{age})")
    void insertUser(User user);

    @Update("UPDATE user SET name = #{name}, age=#{age} WHERE id=#{id}")
    void updateUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(Integer id);

    @Select("SELECT u.*, d.name AS dept_name FROM user u LEFT JOIN department d ON u.dept_id = d.id")
    @Results({
            @Result(property = "deptId", column = "dept_id"), // 映射普通字段
            @Result(property = "department", column = "dept_id",
                    one = @One(select = "com.itheima.backend_demo.DepartmentMapper.findById"))
    })
    List<User> findAllWithDept();

    // 分页查询：从 offset 开始，查 size 条数据
    @Select("SELECT * FROM user LIMIT #{offset}, #{size}")
    List<User> findByPage(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User getUserById(Integer id);
}