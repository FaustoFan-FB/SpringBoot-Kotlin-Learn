package com.faustofan.webview.servlet

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.Filter
import javax.servlet.Servlet

/**
 * @author: fausto
 * @date: 2021/10/23 20:16
 * @description:
 */
@Component
@Configuration
class MyRegisterConfig {

    @Bean //第二种使用原生Web组件的方式
    fun myServlet(): ServletRegistrationBean<Servlet>{
        return ServletRegistrationBean(MyServlet() , "/my")
    }

    @Bean
    fun myFilter(): FilterRegistrationBean<out Filter>{
        //return FilterRegistrationBean(MyFilter() , myServlet())
        return FilterRegistrationBean(MyFilter()).apply {
            urlPatterns = arrayListOf("/my")
        }
    }


    @Bean
    fun myListener(): ServletListenerRegistrationBean<out EventListener>{
        return ServletListenerRegistrationBean(MyServletContextListener())
    }
}