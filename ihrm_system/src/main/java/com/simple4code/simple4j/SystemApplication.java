package com.simple4code.simple4j;


import com.simple4code.simple4j.core.utils.IdWorker;
import com.simple4code.simple4j.core.utils.JwtUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SystemApplication {

    /**
     * 启动方法
     */
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
    /**
     * 解决no session
     */
    //@Bean
    //public OpenEntity
}
