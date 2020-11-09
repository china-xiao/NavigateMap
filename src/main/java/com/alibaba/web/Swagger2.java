package com.alibaba.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/9 14:48
 * @Description：
 * @Version: 0.0.1
 **/
//swagger2的配置文件，在项目的启动类的同级文件建立
@Configuration
@EnableSwagger2
//是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")
public class Swagger2 {
    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .globalOperationParameters(globalOperationParameters())
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.alibaba.web.controller")).paths(PathSelectors.any())
                .build();
    }
    private List<Parameter> globalOperationParameters(){
        ParameterBuilder builder = new ParameterBuilder();
        Parameter token = builder.name("Authorization").description("Authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build();
        ArrayList<Parameter> parameters = new ArrayList<>();
        parameters.add(token);
        return parameters;
    }
    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("遨游物联网导航信息平台API说明文档")
                // 创建人信息
                .contact(new Contact("xiaoxh",  "https://github.com/china-xiao",  "xiaoxh_1@163.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("API 描述")
                .build();
    }
}
