package com.miaosha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description: --------------------------------------
 * @ClassName: MainApplication.java
 * @Date: 2020/6/6 22:12
 * @SoftWare: IntelliJ IDEA
 * --------------------------------------
 * @Author: lixj
 * @Contact: lixj_zj@163.com
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@MapperScan("com.miaosha.base.dao")
public class MainApplication {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(MainApplication.class, args);
    }
}
