package com.itheima.backend_demo;

import com.itheima.backend_demo.service.UserService; // 注意换了导入
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class TestController {

    @Autowired
    private UserService userService; // <--- 这里改成 UserService

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping
    public Result<Void> addUser(@RequestBody User user){
        userService.addUser(user);
        return Result.success(null);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id,@RequestBody User user){
        user.setId(id);
        userService.updateUser(user);
        return "修改成功";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "删除成功";
    }

    @GetMapping("/dept")
    public List<User> getUsersWithDept() {
        return userService.findAllWithDept();
    }

    @GetMapping("/page")
    public List<User> getUsersByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "2") Integer size) {
        return userService.getUserByPage(page, size);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }
}