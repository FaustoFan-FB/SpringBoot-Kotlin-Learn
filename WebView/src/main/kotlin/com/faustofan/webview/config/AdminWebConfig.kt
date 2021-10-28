package com.faustofan.webview.config

import com.faustofan.webview.interceptor.LoginInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author: fausto
 * @date: 2021/10/21 21:05
 * @description:
 */
@Component
@Configuration
class AdminWebConfig: WebMvcConfigurer {

    /**
     *      配置拦截器
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(LoginInterceptor())
            .addPathPatterns("/**")                 //拦截所有,包括静态资源
            .excludePathPatterns("/" , "/login",    //放行的请求
                                "/css/**" , "/fonts/**" , "/images/**" , "/js/**" )
    }
}