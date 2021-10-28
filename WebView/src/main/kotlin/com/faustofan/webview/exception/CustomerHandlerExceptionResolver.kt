package com.faustofan.webview.exception

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author: fausto
 * @date: 2021/10/22 23:27
 * @description:
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE) //设置优先级
@Component
class CustomerHandlerExceptionResolver: HandlerExceptionResolver {

    override fun resolveException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any?,
        ex: Exception,
    ): ModelAndView? {

        response.sendError(511 , "自定义错误")
        return ModelAndView()
    }
}