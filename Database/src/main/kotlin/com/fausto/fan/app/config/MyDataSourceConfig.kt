package com.fausto.app.config

import com.alibaba.druid.pool.DruidDataSource
import com.alibaba.druid.support.http.StatViewServlet
import com.alibaba.druid.support.http.WebStatFilter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

/**
 *    已过时: Druid数据源相关配置,也可以在配置文件中设置
 */
@Deprecated("已过时")
//@Configuration
class MyDataSourceConfig {

    /**
     *      将数据源修改为 Druid
     *      并从配置文件中提取数据库配置
     */
    @ConfigurationProperties("spring.datasource")
    @Bean
    fun dataSource(): DataSource{
        return DruidDataSource().apply {
            /**
             *  在开启监控页功能后 (也可在配置文件中设置)
             *      开启监控功能
             *      开启防火墙功能
             */
            //setFilters("stat , wall")
        }
    }

    /**
     *      开启Druid的--监控页--功能
     */
    @Bean
    fun statViewServlet(): ServletRegistrationBean<StatViewServlet> {
        return ServletRegistrationBean<StatViewServlet>(StatViewServlet() , "/druid/*").apply {
            /**
             *      开启身份验证与权限控制
             */
            addInitParameter("loginUsername","admin")
            addInitParameter("loginPassword","123456")
        }
    }

    /**
     *      采集web-jdbc关联监控的数据
     */
    @Bean
    fun webStatFilter(): FilterRegistrationBean<WebStatFilter>{
        return FilterRegistrationBean(WebStatFilter()).apply {
            urlPatterns = listOf("/*")
            initParameters = mapOf("exclusions" to  "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*")
        }
    }
}