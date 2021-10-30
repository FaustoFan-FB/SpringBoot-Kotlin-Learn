package com.faustofan.webview.config

import com.faustofan.webview.interceptor.LoginInterceptor
import com.faustofan.webview.interceptor.RedisUrlCountInterceptor
import org.springframework.beans.factory.annotation.Autowired
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
     *      因为redis拦截器中
     *      redisTemplate通过注入的方式使用
     *      若此处直接通过new对象的方式创建redis拦截器
     *      将无法访问到该redisTemplate组件
     *      所以此处需注入redis拦截器组件
     */
    @Autowired
    lateinit var redisUrlCountInterceptor: RedisUrlCountInterceptor


    /**
     *      配置拦截器
     */
    override fun addInterceptors(registry: InterceptorRegistry) {

        /**
         *      登录拦截器
         */
        registry.addInterceptor(LoginInterceptor())
            .addPathPatterns("/**")                 //拦截所有,包括静态资源
            .excludePathPatterns("/" , "/login",    //放行的请求
                                "/css/**" , "/fonts/**" , "/images/**" , "/js/**" )


        /**
         *       redis拦截器(uri访问次数记录)
         */
        registry.addInterceptor(redisUrlCountInterceptor)
            .addPathPatterns("/**")                 //拦截所有,包括静态资源
            .excludePathPatterns("/" , "/login",    //放行的请求
                "/css/**" , "/fonts/**" , "/images/**" , "/js/**" )
    }
}