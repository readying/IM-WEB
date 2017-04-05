package com.gd.controller.hello;

import com.google.gson.Gson;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dell on 2017/1/10.
 * Good Luck !
 */
@Controller
@RequestMapping("/test")
@SuppressWarnings("unused")
public class Hello {
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request) {
        //Gson gson = new Gson();
        //return gson.toJson("Hello World!");
        return "hello";
    }

    @RequestMapping(value = "/proxy")
    public String proxy(HttpServletRequest request, HttpServletResponse response) throws  Exception{
       return "proxy";
    }
}
