package com.faustofan.webview.controller

import com.faustofan.webview.bean.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpSession

/**
 * @author: fausto
 * @date: 2021/10/15 23:39
 * @description:
 */
@Controller
class IndexController {
    @GetMapping(value = ["/" ])
    fun loginPage(): String{
        return "login"
    }

    @PostMapping("/login")
    fun index(user: User, session: HttpSession, model: Model): String{
        //登陆验证
        if (user.userName!!.isNotBlank() && ("123456".toInt() == user.password)){
            session.setAttribute("loginUser" , user)
        }else{
            model.addAttribute("msg","账号或密码错误")
            return "login"
        }
        //登陆成功,重定向到index.html
        //重定向防止表单重复提交
        return "redirect:/index.html"
    }

    @GetMapping("/index.html")
    fun indexPage(session: HttpSession , model: Model): String{
        val attribute = session.getAttribute("loginUser")
        //判断用户是否登录
        return if (attribute != null){
            "index"
        }else{
            //返回登陆页面
            model.addAttribute("msg" , "请重新登录")
            "login"
        }

    }

}