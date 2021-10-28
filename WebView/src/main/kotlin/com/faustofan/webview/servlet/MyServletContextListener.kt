package com.faustofan.webview.servlet

import com.faustofan.webview.log.Slf4j.Companion.log
import lombok.extern.slf4j.Slf4j
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

/**
 * @author: fausto
 * @date: 2021/10/23 20:08
 * @description:
 *          使用WEB原生组件:
 *              1. 方式一
 *                  启动类使用@ServletComponentScan(basePackages = ["com.faustofan.application"])
 *                  原生组件类使用相应注解
 *
 *              2. 方式二
 *                  在配置类中注入原生组件
 *
 */
@Slf4j
//@WebListener
class MyServletContextListener: ServletContextListener {

    override fun contextInitialized(sce: ServletContextEvent?) {
        log.info("监听到项目初始化完成....................")
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        log.info("监听到项目销毁.................")
    }
}