package com.faustofan.webview.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author: fausto
 * @date: 2021/10/22 23:08
 * @description:
 */

@ResponseStatus(value = HttpStatus.FORBIDDEN , reason = "用户存在问题")
class UserException(override val message: String?): RuntimeException(message) {
    constructor(): this("")
}