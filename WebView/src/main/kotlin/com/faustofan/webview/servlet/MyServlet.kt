package com.faustofan.webview.servlet

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author: fausto
 * @date: 2021/10/23 19:22
 * @description:
 *          使用WEB原生组件:
 *              1. 方式一
 *                  启动类使用@ServletComponentScan(basePackages = ["com.faustofan.application"])
 *                  原生组件类使用相应注解
 *
 *              2. 方式二
 *                  在配置类中注入原生组件
 */

//@WebServlet(urlPatterns = ["/my"])  //第一种方式

class MyServlet: HttpServlet() {
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        resp?.writer?.write("66666")
    }
}