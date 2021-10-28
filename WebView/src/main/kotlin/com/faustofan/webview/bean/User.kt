package com.faustofan.webview.bean

/**
 * @author: fausto
 * @date: 2021/10/18 15:03
 * @description:
 */
data class User(var userName: String , var password: Int){
    constructor(): this("",0)
}