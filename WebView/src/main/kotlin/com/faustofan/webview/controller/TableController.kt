package com.faustofan.webview.controller

import com.faustofan.webview.exception.UserException
import com.faustofan.webview.bean.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author: fausto
 * @date: 2021/10/17 21:39
 * @description:
 */
@Controller
class TableController {

    @GetMapping("/basic_table")
    fun basicTable(): String{

        return "table/basic_table"
    }

    @GetMapping("/dynamic_table")
    fun dynamicTable(model: Model): String{
        /**
         *      表格的动态遍历
         */
        val users = arrayListOf<User>(
            User("fausto" , 22),
            User("刘备" , 42),
            User("关羽" , 32),
            User("张飞" , 21),
            User("黄忠" , 52),
            User("赵芸" , 27),
            User("姜维" , 20),
        ).apply {
            model.addAttribute("users" , this)
        }

        if (users.size > 5){
            throw UserException("用户数量过多...")
        }

        return "table/dynamic_table"
    }



    @GetMapping("/responsive_table")
    fun responsiveTable(): String{

        return "table/responsive_table"
    }

    @GetMapping("/editable_table")
    fun editableTable(): String{

        return "table/editable_table"
    }
}