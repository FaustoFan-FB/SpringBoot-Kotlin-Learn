package com.faustofan.webview.controller

import com.faustofan.webview.bean.Person
import com.faustofan.webview.bean.Pet
import org.springframework.web.bind.annotation.*
import java.util.*

//import javax.servlet.http.Cookie

/**
 * @author: fausto
 * @date: 2021/10/10 19:21
 * @description:
 */
@RestController
class Parameter {

    // car/55/owner/fausto?age=18&inters=basketball&inters=game
    @RequestMapping("/car/{id}/owner/{username}")
    fun getCar(
        /**
         * 路径变量注解
         */
        @PathVariable("id") id: Int ,                     //将路径变量中的id信息保存到函数形参id中
        @PathVariable("username") name: String ,
        @PathVariable pv: Map<String,String> ,            //将所有路径变量保存到函数形参pv中,形参类型必须是键值对为字符串的Map
        /**
         * 请求头注解
         */
        @RequestHeader("User-Agent") userAgent: String ,  //将请求头中User_Agent信息保存到形参userAgent中
        @RequestHeader header: Map<String,String> ,       //将请求头中所有信息保存到形参header中,形参类型必须是键值对为字符串的Map
        /**
         * 请求参数注解
         */
        @RequestParam("age") age: Int ,                   //将请求参数中的age保存到形参age中
        @RequestParam("inters") inters: List<String> ,
        @RequestParam params: Map<String,String> ,        //将请求参数信息保存到形参params中,形参类型必须是键值对为字符串的Map
        /**
         * Cookie参数注解
         */
        //@CookieValue("_ga") _ga: String  ,                //将cookie中的_ga值保存到形参_ga中
        //@CookieValue cookie: Cookie                       //将cookie中的所有信息保存到形参cookie中,形参类型必须是Cookie

    ): Map<String,Any>{

        return HashMap<String,Any>().apply {
            put("id"        , id        )
            put("name"      , name      )
            put("pv"        , pv        )
            put("userAgent" , userAgent )
            put("header"    , header    )
            put("age"       , age       )
            put("inters"    , inters    )
            put("params"    , params    )
            //put("_ga"       , _ga       )
            //put("cookie"    , cookie    )
        }
    }



    @PostMapping("/save")
    fun postMethod(
        /**
         * 请求体 , 只有以post方式提交的表单才携带有请求体
         */
        @RequestBody content: String        //将请求体中信息保存到形参content中

    ): Map<String,Any>{

        return HashMap<String, Any>().apply {
            put("content" , content)
        }

    }

    /**
     *  1. car/{path}?xxx=xxx&xxx=xxx  此种url称为 queryString
     *  2. car/{path;xxx=xxx;xxx=xxx}  此种url为矩阵变量形式
     *  3. 用处:
     *          session --> jsessionid --> cookie
     *          用户在服务器中的sessionid保存在cookie中,当cookie在浏览器中被禁用时,服务端就无法得知用户对应的session
     *          此时便可通过矩阵变量携带sessionid
     *          如:  car/id;jsessionid/xxx=xxx&xxx=xxx
     *
     *
     *
     *  手动开启矩阵变量:
     *      原理: 对于路径的处理,都交给WebAutoConfig配置类中的
     *              UrlPathHelper进行解析
     *              其中的removeSemicolonContent方法(默认为true) 为移除分号设置
     *              与 矩阵变量相关
     *      设置方法:
     *          自定义WebConfig配置类取代提供的WebMvcConfigurer
     *          1. 自定义类实现WebMvcConfigurer (实现方法: configurePathMatch) 配置路径映射规则
     *
     */
    // cars/sell;low=34;brand=byd,audi,yd
    @ResponseBody
    @GetMapping("/cars/{path}")

    fun carsSell(
        @MatrixVariable("low") low: Int,
        @MatrixVariable("brand") brand: List<String>,
        @PathVariable("path") path: String

    ): Map<String,Any>{

        return HashMap<String, Any>().apply {
            put("low"   , low)
            put("brand" , brand)
            put("path"  , path)
        }
    }

    /**
     *      矩阵变量中有同名值情况
     *      如: boss/1;age=33/2;age=44
     */
    @ResponseBody
    @GetMapping("/boss/{bossId}/{empId}")

    fun boss(
        @PathVariable bossId: String,
        @PathVariable empId: String,
        @MatrixVariable(value = "age" , pathVar = "bossId") bossAge: Int,  //可通过pathVar指定路径变量,从而确定矩阵变量
        @MatrixVariable(value = "age" , pathVar = "empId") empAge: Int,    //同上

    ): Map<String,Any>{
        return HashMap<String, Any>().apply {
            put("bossId" , bossId )
            put("empId"  , empId  )
            put("bossAge" , bossAge )
            put("empAge" , empAge )
        }
    }



    /**
     *      数据绑定: 页面提交的请求数据(GET,POST)都可以和对象属性进行绑定
     */
    @PostMapping("/saveuser")
    fun saveuser(person: Person): Person {
        return person
    }

    @GetMapping("/test/converter")
    fun getPerson(): Person {
        return Person().apply {
            username = "fausto"
            age = 34
            birth = Date(2000,2,4)
            pet = Pet("tom",4)
        }
    }

}