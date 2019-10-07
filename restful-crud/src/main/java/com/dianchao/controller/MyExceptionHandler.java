package com.dianchao.controller;

import com.dianchao.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

//异常处理器
@ControllerAdvice
public class MyExceptionHandler {

    //1.浏览器客户端返回的都是json
//    @ResponseBody //返回json数据
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(Exception e){
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//        return map;
//    }

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //传入自己的状态错误码 4xx 5xx, 否则就不会进入定制错误页面的解析流程
        /**
         * BasicErrorController.java
         * Integer statusCode = (Integer) request
         *  .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("code", "user.notexist");
        map.put("message", "用户出错啦");

        request.setAttribute("ext", map);
        //转发到/error
        return "forward:/error";
    }
}
