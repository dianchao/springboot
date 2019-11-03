package com.dianchao.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 定制请求的授权规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/level1/**")
                .hasRole("VIP1")
                .antMatchers("/level2/**")
                .hasRole("VIP2")
                .antMatchers("/level3/**")
                .hasRole("VIP3");

        //开启http自动登录功能，效果：如果没有权限，就会来到登录页面
        //1）/login来到登录页
        //2) 重定向到/login?error表示登录失败
        //3) 更多详细规定
        //http.formLogin();
        //指定登录页面为自己定义的页面
        //注意：一旦定制loginPage，那么loginPage页面的post请求就是登录
        http.formLogin().usernameParameter("userName").passwordParameter("pwd").loginPage("/userlogin");

        //开启自动配置的自动注销功能
        //1)访问/logout表示用户注销，清空session
        //2)默认注销成功会返回/login?logout页面
        http.logout().logoutSuccessUrl("/"); //注销成功来到首页

        //开启记住我功能
        //登录成功后，将cookie发给浏览器保存，以后访问页面会带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie
        http.rememberMe().rememberMeParameter("remember");

    }

    /**
     * 定制认证规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1", "VIP3");
    }
}
