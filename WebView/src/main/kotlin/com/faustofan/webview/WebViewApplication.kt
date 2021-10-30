package com.faustofan.webview

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@MapperScan(value = ["com.faustofan.webview.mapper"])
@SpringBootApplication
class WebViewApplication

fun main(args: Array<String>) {
    runApplication<WebViewApplication>(*args)
}