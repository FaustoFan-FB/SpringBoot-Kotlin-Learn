package com.faustofan.webview.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author: fausto
 * @date: 2021/10/10 21:11
 * @description:
 */
@Controller

class Request {

    @GetMapping("/goto")
    fun goToPage(request: HttpServletRequest): String{
        request.setAttribute("msg" , "成功...")  //为请求对象添加属性
        request.setAttribute("code" , 200)
        return "forward:/success"                         //转发至/success
    }


    /**
     *  controller的方法参数类型为: Map , Model 时,
     *  会自动将这些类型的值保存到请求对象的域中
     *
     */
    @GetMapping("/params")
    fun testParse(

        map: Map<String,Any>,
        model: Model,
        request: HttpServletRequest,
        response: HttpServletResponse

    ): String{

        (map as HashMap)["map1"] = "mapGreet=============="
        map["map2"] = "map2======================"
        model.addAttribute("model1" ,"modelGreet===========")
        model.addAttribute("model2" , "model2===================" )
        request.setAttribute("msg","requestGreet==============")
        response.addCookie(Cookie("c1","v1").apply { domain = "localhost" })

        //经检验,请求对象的属性一种类型只能保有一个值,后来的添加的属性值会覆盖原先的.

        return "forward:/success"
    }


    @ResponseBody
    @GetMapping("/success")
    fun success(

        @RequestAttribute(value = "msg" , required = false/*这里表明,该请求属性不是必须的,测试/params请求时设置*/) msg: String ,//将请求对象中msg属性的值赋给形参msg
        @RequestAttribute(value = "code", required = false) code: Int ,              //将请求对象中code属性的值赋给形参code
        request: HttpServletRequest,                         //获取请求对象
        response: HttpServletResponse

    ): Map<String,Any>{

        return HashMap<String, Any>().apply {
            //put("reqMethod_msg" , msg)
            //put("annotation_msg" , request.getAttribute("msg"))

            put("model"     ,   request.getAttribute("map1"  )  )
            put("model"     ,   request.getAttribute("map2"  )  )
            put("map"       ,   request.getAttribute("model1")  )
            put("map"       ,   request.getAttribute("model2")  )
            put("request"   ,   request.getAttribute("msg"   )  )
            put("response"  ,   response.status                       )
        }
    }
}