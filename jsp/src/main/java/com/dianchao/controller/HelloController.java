package com.dianchao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    //当前项目下的abc请求
    @GetMapping("/abc")
    public String hello(Model model){
        System.out.println("hello world!");
        model.addAttribute("msg", "你好");
        return "success";
    }
}
