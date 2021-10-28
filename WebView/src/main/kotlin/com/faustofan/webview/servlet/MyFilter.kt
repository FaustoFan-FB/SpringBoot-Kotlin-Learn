package com.faustofan.webview.servlet

import com.faustofan.webview.log.Slf4j.Companion.log
import lombok.extern.slf4j.Slf4j
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * @author: fausto
 * @date: 2021/10/23 20:01
 * @description:
 *         使用WEB原生组件:
 *              1. 方式一
 *                  启动类使用@ServletComponentScan(basePackages = ["com.faustofan.application"])
 *                  原生组件类使用相应注解
 *
 *              2. 方式二
 *                  在配置类中注入原生组件
 */
@Slf4j
//@WebFilter(urlPatterns = ["/css/*" , "/images/*"])

class MyFilter: Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        log.info("myFilter工作...")
        chain?.doFilter(request,response)
    }
}