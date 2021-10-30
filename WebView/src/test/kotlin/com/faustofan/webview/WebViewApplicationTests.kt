package com.faustofan.webview

import com.faustofan.webview.log.Slf4j
import com.faustofan.webview.log.Slf4j.Companion.log
import com.faustofan.webview.mapper.UserMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.redis.core.StringRedisTemplate


@Slf4j
@SpringBootTest
class WebViewApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Autowired
    lateinit var userMapper: UserMapper
    @Test
    fun testUserMapper(){
        userMapper.selectById(1L).apply {
            log.info("\n+++++++++++++++++++++++++++"+
                     "\n+---查询字段---id=1L---结果: $this" +
                     "\n+++++++++++++++++++++++++++")
        }
    }

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate
    @Test
    fun testRedis(){
        redisTemplate.opsForValue().apply {
            set("Hello" , "World")

            log.info("\n+++++++++++++++++++++++++++"+
                    "\n+---Redis查询---结果: ${this.get("Hello")}" +
                    "\n+++++++++++++++++++++++++++")
        }
    }



}