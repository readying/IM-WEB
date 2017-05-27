package com.gd.controller.hello;

import com.google.gson.Gson;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dell on 2017/1/10.
 * Good Luck !
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/test")
@SuppressWarnings("unused")
public class Hello {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @ApiIgnore
    public String index(HttpServletRequest request) {
        Gson gson = new Gson();
        //return gson.toJson("Hello World!");
        return "Hello World!";
    }
}
