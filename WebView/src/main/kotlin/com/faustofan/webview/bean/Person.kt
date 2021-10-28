package com.faustofan.webview.bean

import java.util.*

/**
 * @author: fausto
 * @date: 2021/10/12 20:03
 * @description:
 */
data class Person(
    var username: String?,
    var age: Int?,
    var birth: Date?,
    var pet: Pet?
){

    constructor(): this("",0, Date(), Pet())

}