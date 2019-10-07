package com.dianchao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HelloController {

    //@RequestMapping({"/", "/index.html"})
    //public String index() {
    //    return "index";
    //}

    //@ResponseBody
    //@RequestMapping("/hello")
    //public String hello(){
    //    return  "hello world";
    //}

    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        return "success";
    }

//    @ResponseBody
//    @RequestMapping("/hello")
//    public String hello(@RequestParam("user")String user){
//        if("aaa".equals(user)){
//            throw  new UserNotExistException();
//        }
//        return "hello world";
//    }
}
