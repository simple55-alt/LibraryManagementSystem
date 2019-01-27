package com.winter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;


@SpringBootApplication
@MapperScan("com.winter.dao")//将项目中对应的mapper类的路径加进来就可以了
public class Springboot2MybatisDemoApplication {

    @Autowired
    DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(Springboot2MybatisDemoApplication.class, args);
    }

    /**
     * 查看用了什么数据库连接池
     */
    public void run(String... args) throws Exception {
        System.out.println("DATASOURCE = " + dataSource);
    }

}
