package com.faustofan.webview.converter

import com.faustofan.webview.bean.Person
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.lang.Nullable

/**
 * @author: fausto
 * @date: 2021/10/13 21:14
 * @description:    自定义converter
 */

class FaustoConverter: HttpMessageConverter<Person>{
    override fun canRead(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return false
    }

    override fun canWrite(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return clazz.isAssignableFrom(Person::class.java)
    }

    override fun getSupportedMediaTypes(): MutableList<MediaType> {
        return MediaType.parseMediaTypes("application/fxml")
    }

    override fun read(clazz: Class<out Person>, inputMessage: HttpInputMessage): Person {
        return Person()
    }

    override fun write(person: Person, @Nullable contentType: MediaType?, outputMessage: HttpOutputMessage) {
        var data = "${person.username};${person.age};${person.birth};${person.pet}"
        outputMessage.body.write(data.toByteArray())
    }
}