package com.faustofan.webview.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import java.io.File

/**
 * @author: fausto
 * @date: 2021/10/21 21:49
 * @description:
 */
@Controller
class FormController {

    @GetMapping("/form_layouts")
    fun formLayouts(

    ): String{

        return "form/form_layouts"
    }


    /**
     *      文件上传服务
     *          1. MultipartFile: 自动封装上传的文件
     */
    @PostMapping("/upload")
    fun upload(
        @RequestParam email: String ,
        @RequestParam username: String ,
        @RequestPart headerImage: MultipartFile ,
        @RequestPart photos: ArrayList<MultipartFile>

    ): String{

        println("=======================================")
        println("上传的信息:\n" +
                "email= $email \n" +
                "username= $username \n" +
                "headerImage= ${headerImage.size} \n" +
                "photos= ${photos.size} \n")

        if (!headerImage.isEmpty){
            val fileName = headerImage.originalFilename
            headerImage.transferTo(File("F:\\Project\\Kotlin_project\\demo3_thymeleaf\\src\\test\\image\\$fileName"))
        }

        if (photos.size > 0){
            photos.forEach { photo ->
                if (!photo.isEmpty){
                    photo.originalFilename.let {
                        photo.transferTo(File("F:\\Project\\Kotlin_project\\demo3_thymeleaf\\src\\test\\image\\$it"))
                    }
                }
            }
        }

        return "index"
    }

}