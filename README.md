企业级用户管理系统 (Enterprise User Management System)

项目简介
基于 Spring Boot 3 + MyBatis 独立开发的后端项目，采用标准的 Controller-Service-Mapper 三层架构，实现前后端分离的 RESTful API 接口开发。

技术栈
- 核心框架：Java 21, Spring Boot 3.3.4
- 持久层：MyBatis, MySQL
- 构建工具：Maven
- 版本控制：Git

核心功能
- 基础 CRUD：实现用户信息的增删改查。
- 多表关联查询：实现 User 与 Department 的多表关联，完成复杂对象映射。
- 分页查询：基于 LIMIT 实现数据分页展示。
- 全局异常处理：基于 `@RestControllerAdvice` 统一拦截异常，规范前后端 JSON 数据返回格式。
- 参数校验：在 Service 层实现基础业务校验，拦截非法数据。

运行指南
1. 克隆代码：`git clone https://github.com/MaoTiaoOu1Xi/backend-demo.git`
2. 在 MySQL 中创建数据库 `user_crud_test`，并执行建表语句。
3. 修改 `src/main/resources/application.properties` 中的数据库账号密码。
4. 运行 `BackendDemoApplication.java` 启动项目。
