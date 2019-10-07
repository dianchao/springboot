package com.dianchao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    /**
     * @DeleteMapping
     * @PutMapping
     * @GetMapping
     * @PostMapping
     * @PostMapping(value = "/user/login") 等价@RequestMapping(value = "/user/login", method = RequestMethod.POST)
     */
    //@RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功
            //System.out.println("username: " + username + ",password: " + password);
            //表单重复提交问题
            //return "dashboard";
            System.out.println("username: " + username + ", password: " + password);
            session.setAttribute("loginUser", username);
            //避免表单重复提交, 可以重定向到主页
            return "redirect:/main.html";
        }else{
            //登录失败
            map.put("msg", "用户名和密码错误");
            return "login";
        }
    }
}
