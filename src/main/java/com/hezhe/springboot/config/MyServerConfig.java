package com.hezhe.springboot.config;

import com.hezhe.springboot.servlert.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 贺哲
 * @2020-01-07 17:17
 */
@Configuration
public class MyServerConfig {

    /**
     * 配置servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return servletRegistrationBean;
    }
}
