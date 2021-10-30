package com.faustofan.webview.bean

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

/**
 * @author: fausto
 * @date: 2021/10/18 15:03
 * @description:
 */
@TableName("user")      //若表名与类名相同可省略不写
data class User(
    //临时字段,在数据库表中不存在的字段
    @TableField(exist = false)
    var userName: String?,
    @TableField(exist = false)
    var password: Int?,

    //数据库字段
    var id: Long?,
    var name: String?,
    var age: Int?,
    var email: String?
){
    constructor(): this("",0,0,"",0,"")
}