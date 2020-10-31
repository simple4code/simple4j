package com.simple4code.simple4j.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.simple4code.simple4j.core.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Chen
 * @created 2020-11-01-0:33.
 */
public class BaseWebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper() {
        JsonMapper.Builder builder = JsonMapper.builder();
        // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。这样对移动端会更省流量
        // Include.NON_NULL 属性为NULL 不序列化
        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        //有未知属性 要不要抛异常
        builder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //是否允许JSON字符串包含未转义的控制字符(值小于32的ASCII字符，包括制表符和换行符)的特性。如果feature设置为false，则在遇到这样的字符时抛出异常。
        builder.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS, true);
        //确定解析器是否允许使用单引号(撇号，字符'\ ")引用字符串(名称和字符串值)的特性。如果是，这是除了其他可接受的标记。但不是JSON规范)。
        builder.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        JsonMapper jsonMapper = builder.build();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //添加 序列器  这里是对 BigDecimal 类型处理，这是关键代码
        javaTimeModule.addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {
            @Override
            public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                DecimalFormat fnum = new DecimalFormat("#.##");
                //把 这个BigDecimal 属性对应的值，写成 String 类型
                gen.writeString(fnum.format(value));
            }
        });
        //处理 时间格式
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //注册
        jsonMapper.registerModule(javaTimeModule);
        return jsonMapper;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //1.添加自定义拦截器
        //2.指定拦截器的url地址
        //3.指定不拦截的url地址
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/sys/login", "/frame/register/**", "/v2/api-docs",
                        "/api-docs",
                        "/api-docs-ext",
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/configuration/**",
                        "/webjars/**",
                        "/public",
                        "/swagger-ui/**",
                        "/doc.html");
    }
}
