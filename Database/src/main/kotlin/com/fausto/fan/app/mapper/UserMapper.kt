package com.fausto.fan.app.mapper

import com.fausto.fan.app.entity.User
import org.apache.ibatis.annotations.Mapper

/**
 * @author: fausto
 * @date: 2021/10/27 23:06
 * @description:
 */
@Mapper
interface UserMapper {

    fun getUser(id: Long): User
}