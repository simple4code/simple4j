package com.simple4code.simple4j.core.config;

/**
 * @author Chen
 * @created 2020-08-02-12:27.
 */
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //定义用户信息服务
    //@Bean
    //public UserDetailsService userDetailsService() {
    //    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //    manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
    //    manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
    //    return manager;
    //}
    //
    //// 密码编码器
    //@Bean
    //public PasswordEncoder passwordEncoder() {
    //    return NoOpPasswordEncoder.getInstance();
    //}
    //
    //// 配置拦截
    //@Override
    //protected void configure(HttpSecurity httpSecurity) throws Exception {
    //    httpSecurity.authorizeRequests()
    //            // 要求访问/r/r1必须有p1权限
    //            .antMatchers("/r/r1").hasAuthority("p1")
    //            // 要求访问/r/r2必须有p2权限
    //            .antMatchers("/r/r2").hasAuthority("p2")
    //            // 所有/r/**都必须认证才能通过
    //            .antMatchers("/r/**").authenticated()
    //            // 其余可以访问
    //            .anyRequest().permitAll()
    //            .and()
    //            // 允许表单登录
    //            .formLogin()
    //            // 自定义成功地址
    //            .successForwardUrl("/login-success");
    //}

//}
