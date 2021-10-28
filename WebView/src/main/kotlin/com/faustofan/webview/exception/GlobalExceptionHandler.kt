package com.faustofan.webview.exception

import com.faustofan.webview.log.Slf4j.Companion.log
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author: fausto
 * @date: 2021/10/22 22:27
 * @description: 处理整个Web Controller的异常
 */

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler(value = [ArithmeticException::class , NullPointerException::class])
    fun handleMathException( e: Exception ): String{

        log.error("========异常是:->$e ====================")

        //返回视图
        return "login"
    }
}