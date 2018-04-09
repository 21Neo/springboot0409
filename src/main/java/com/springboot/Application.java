package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 说明是一个 Spring Boot 应用程序
@SpringBootApplication
// 扫描器 (不需要在每个mapper文件下注解@Responsibility)
@MapperScan("com.springboot.mapper")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
