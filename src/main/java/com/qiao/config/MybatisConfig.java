package com.qiao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value={"com.qiao.users.dao"})
public class MybatisConfig {
}
