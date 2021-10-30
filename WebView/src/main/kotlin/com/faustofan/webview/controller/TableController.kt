package com.faustofan.webview.controller

import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.faustofan.webview.bean.User
import com.faustofan.webview.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

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

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/dynamic_table")
    fun dynamicTable(model: Model , @RequestParam(value = "pn" , defaultValue = "1")pn: Int ): String{
        /**
         *      表格的动态遍历
         */
        /*userService.list().apply {
            model.addAttribute("users" , this)
        }*/

        /**
         *      分页数据
         */
        //分页查询结果: 其中的records为查询到的所有用户记录->可替代上文的users
        val userPage: Page<User> = userService.page(Page(pn.toLong(), 2.toLong()), null)
        model.addAttribute("users", userPage)

        return "table/dynamic_table"
    }



    @GetMapping("/user/delete/{id}")
    fun deleteUser(
        @PathVariable(value = "id") id: String ,
        @RequestParam(value = "pn" , defaultValue = "1") pn: Int ,
        ra: RedirectAttributes

    ): String{

        userService.removeById(id.toLong())

        //设置重定向所携带的参数(页码数,实现删除时能返回当前页面)
        ra.addAttribute("pn",pn)
        return "redirect:/dynamic_table"
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