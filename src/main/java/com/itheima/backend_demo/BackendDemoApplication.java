package com.itheima.backend_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // <--- 加上这个注解，开启缓存
public class BackendDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackendDemoApplication.class, args);
	}
}