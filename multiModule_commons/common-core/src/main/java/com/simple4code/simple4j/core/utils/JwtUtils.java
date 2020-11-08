package com.simple4code.simple4j.core.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
//@ConfigurationProperties("jwt.config")
//@Configuration(value = "jwt.config")
public class JwtUtils {

    @Value("${jwt.config.key}")
    public String key;

    @Value("${jwt.config.ttl}")
    public long ttl;

    @Value("${jwt.config.expirationSeconds}")
    public Integer expirationSeconds;

    @Value("${jwt.config.validTime}")
    public Integer validTime;

    @Value("${jwt.config.prefix}")
    public String prefix;

    @Value("${jwt.config.header}")
    public String header;

    /**
     * 设置认证token
     * id:登录用户id
     * subject：登录用户名
     */
    public String createJwt(String id, String name, Map<String, Object> map) {
        //1.设置失效时间,当前毫秒
        long now = System.currentTimeMillis();
        long exp = now + ttl;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);
        //3.根据map设置claims
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            jwtBuilder.claim(entry.getKey(), entry.getValue());
        }
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析token字符串获取clamis
     */
    public Claims parseJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }


    //// 寻找证书文件
    //private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks"); // 寻找证书文件
    //private static PrivateKey privateKey = null;
    //private static PublicKey publicKey = null;
    //static { // 将证书文件里边的私钥公钥拿出来
    //    try {
    //        KeyStore keyStore = KeyStore.getInstance("JKS"); // java key store 固定常量
    //        keyStore.load(inputStream, "123456".toCharArray());
    //        privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray()); // jwt 为 命令生成整数文件时的别名
    //        publicKey = keyStore.getCertificate("jwt").getPublicKey();
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}


    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }


    /**
     * 生成token
     *
     * @param subject           （主体信息）
     * @param expirationSeconds 过期时间（秒）
     * @param claims            自定义身份信息
     * @return
     */
    public String createToken(String subject, int expirationSeconds, Map<String, Object> claims) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationSeconds * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS512, salt) // 不使用公钥私钥
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    //public Authentication getAuthentication(String token) {
    //    UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
    //    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    //}
    //
    //public String getUsername(String token) {
    //    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    //}


    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(header);
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * @author: zzx
     * @date: 2018-10-19 09:10
     * @deprecation: 解析token, 获得subject中的信息
     */
    public String parseToken(String token, String salt) {
        String subject = null;
        try {
            /*Claims claims = Jwts.parser()
//                    .setSigningKey(salt) // 不使用公钥私钥
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();*/
            subject = getTokenBody(token).getSubject();
        } catch (Exception e) {
        }
        return subject;

    }
    //
    ///**
    // * 是否过期
    // * @param token
    // * @return
    // * @throws CommonException
    // */
    //public boolean validateToken(String token) throws CommonException {
    //    try {
    //        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    //        return true;
    //    } catch (JwtException | IllegalArgumentException e) {
    //        throw new CommonException(ResultCode.USER_TOKEN_EXPIRED);
    //    }
    //}

    /**
     * 是否过期
     *
     * @param expirationTime
     * @return
     * @throws
     */
    public boolean validateToken(String expirationTime) {
        if (DateUtil.compare(new DateTime(), DateUtil.parse(expirationTime, DatePattern.NORM_DATE_PATTERN)) > 0) {
            //当前时间比过期时间小，失效
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取token自定义属性
     *
     * @param token
     * @return
     */
    public Map<String, Object> getClaims(String token) {
        Map<String, Object> claims = null;
        try {
            claims = getTokenBody(token);
        } catch (Exception e) {
        }

        return claims;

    }

    private Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}