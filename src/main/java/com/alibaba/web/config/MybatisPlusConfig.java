package com.alibaba.web.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan(value = {"com.alibaba.**.dao"})
public class MybatisPlusConfig {

    /**
     * 分页插件配置
     * @author weiyibin
     * @date 2019年1月9日10:46:43
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
