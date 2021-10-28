package com.faustofan.webview.interceptor

import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author: fausto
 * @date: 2021/10/21 20:55
 * @description:
 *              登录检查:
 *             1. 编写一个拦截器实现 HandlerInterceptor
 *             2. 将拦截器注册到容器中 (实现WebMvcConfigurer的addInterceptor方法)
 *             3. 指定拦截规则 (拦截所有,指明放行内容如静态资源登陆页面)
 */
class LoginInterceptor: HandlerInterceptor {

    /**
     *      1. 目标方法执行之前
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        /**
         *      登录检查逻辑:
         *          返回真->放行
         *          返回假->拦截
         */

        return if(request.session.getAttribute("loginUser") != null){
            true
        }else{
            //已拦截
            request.setAttribute("msg" , "请先登录...")
            //转发到登录页面
            request.getRequestDispatcher("/").forward(request,response)
            false
        }
    }


    /**
     *      2. 目标方法执行之后
     */
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {
        super.postHandle(request, response, handler, modelAndView)
    }


    /**
     *      3. 页面渲染之后
     */
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?,
    ) {
        super.afterCompletion(request, response, handler, ex)
    }
}