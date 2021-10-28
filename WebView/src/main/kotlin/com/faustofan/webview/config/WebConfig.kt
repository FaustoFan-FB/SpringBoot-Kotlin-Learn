package com.faustofan.webview.config

import com.faustofan.webview.bean.Pet
import com.faustofan.webview.converter.FaustoConverter
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.util.StringUtils
import org.springframework.web.accept.HeaderContentNegotiationStrategy
import org.springframework.web.accept.ParameterContentNegotiationStrategy
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.util.UrlPathHelper

/**
 * @author: fausto
 * @date: 2021/10/10 22:16
 * @description: 自定义一些解析逻辑
 */


@Configuration(proxyBeanMethods = false)
class WebConfig: WebMvcConfigurer {

    /**
     *      开启矩阵变量: 方式一
     */
    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.setUrlPathHelper(UrlPathHelper().apply {
            setRemoveSemicolonContent(false) //不移除路径中分号后的内容
        })
    }


    /**
     *      添加格式化转化器用以解析
     *      <input name="pet" value="petName,petAge">
     */
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(object : Converter <String, Pet>{
            override fun convert(source: String): Pet? {
                if (!StringUtils.isEmpty(source)){
                    val list = source.split(",")
                    return Pet().apply {
                        name = list[0]
                        age  = list[1].toInt()
                    }
                }
                return null
            }
        })
    }

    /**
     *      添加自定义内容协商器,以支持返回自定义协议数据
     */
    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(FaustoConverter())
    }

    /**
     *      若自定义了:
     *              支持参数中定义内容协商格式
     *              或请求头中定义内容协商格式
     *              中的一个又以未自定义的方式
     *              发出对相应格式的内容请求,则会默认返回json数据
     *      例:
     *              请求参数:  localhost:8080/xxx/xxx?format=fxml
     *              请求头:    Accept = "application/fxml"
     */
    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        /**
         *  自定义-请求参数-内容协商器:
         *      指定支持解析哪些参数对应的哪些媒体类型
         */
        ParameterContentNegotiationStrategy(HashMap<String,MediaType>().apply {
            put("json",MediaType.APPLICATION_JSON)
            put("xml",MediaType.APPLICATION_XML)
            put("fxml",MediaType.parseMediaType("application/fxml"))
        }).let {
            configurer.strategies(listOf(it,HeaderContentNegotiationStrategy()))
        }
        /**
         *  自定义-请求头-内容协商器: 见listOf(x , HeaderContentNegotiationStrategy)
         */
    }


}




//开启矩阵变量: 方法2
/*
@Configuration(proxyBeanMethods = false)
class WebConfig2{

    @Bean
    fun webMvcConfigurer(): WebMvcConfigurer{
        return object : WebMvcConfigurer{
            override fun configurePathMatch(configurer: PathMatchConfigurer) {
                configurer.setUrlPathHelper(UrlPathHelper().apply {
                    setRemoveSemicolonContent(false) //不移除路径中分号后的内容
                })
            }

        }
    }
}*/