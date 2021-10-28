package com.fausto.fan.app.log

/**
 * @author: fausto
 * @date: 2021/10/22 22:52
 * @description:
 */
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Slf4j{
    companion object{
        val <reified T> T.log: Logger
            inline get() = LoggerFactory.getLogger(T::class.java)
    }
}