package com.fausto.fan.app.controller

import com.fausto.fan.app.entity.User
import com.fausto.fan.app.log.Slf4j
import com.fausto.fan.app.log.Slf4j.Companion.log
import com.fausto.fan.app.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author: fausto
 * @date: 2021/10/25 20:42
 * @description:
 */
@Slf4j
@Controller
class SqlController {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    lateinit var userService: UserService

    @ResponseBody
    @GetMapping("/user")
    fun getById(@RequestParam id: String): User {
        return userService.getUserById(id.toLong())
    }


    @ResponseBody
    @GetMapping("/query")
    fun queryFromDB(): Map<String,List<String>>{
        log.info("\n******开始查询--select * from user_info;********")

        val idList   =  jdbcTemplate.queryForList("select id from user_info;"  , String::class.java )
        val nameList =  jdbcTemplate.queryForList("select name from user_info;", String::class.java )
        val ageList  =  jdbcTemplate.queryForList("select age from user_info;" , String::class.java )

        return mutableMapOf(
            "id"   to idList   ,
            "name" to nameList ,
            "age"  to ageList
        )
    }
}