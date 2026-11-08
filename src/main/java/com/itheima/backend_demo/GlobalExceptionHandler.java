package com.itheima.backend_demo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 这个注解是核心，代表拦截所有 Controller 的异常
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 拦截所有 Exception
    public Result<String> handleException(Exception e) {
        // 如果出错了，统一返回这个格式
        return Result.error(500, e.getMessage());
    }
}