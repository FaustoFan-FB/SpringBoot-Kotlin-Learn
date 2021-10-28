package com.fausto.fan.app.entity

/**
 * @author: fausto
 * @date: 2021/10/27 23:05
 * @description:
 */


data class User(var id: Int , var name: String , var age: Int){
    constructor(): this(0,"",0)
}