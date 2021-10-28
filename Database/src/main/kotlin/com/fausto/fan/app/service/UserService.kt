package com.fausto.fan.app.service


import com.fausto.fan.app.entity.User
import com.fausto.fan.app.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author: fausto
 * @date: 2021/10/27 20:35
 * @description:
 */
@Service
class UserService {
    @Autowired
    lateinit var userMapper: UserMapper

    fun getUserById(id: Long): User {
        return userMapper.getUser(id);
    }
}