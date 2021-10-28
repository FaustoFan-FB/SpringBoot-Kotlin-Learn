package com.faustofan.webview.bean

/**
 * @author: fausto
 * @date: 2021/10/12 20:03
 * @description:
 */

data class Pet(
    var name: String?,
    var age:  Int?
){
    constructor(): this("",0)
}