package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author: Ynw
 * @Date: 2023/04/04/14:41
 * @Description:
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.dao"})
//@ServletComponentScan
public class MobileApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }
}