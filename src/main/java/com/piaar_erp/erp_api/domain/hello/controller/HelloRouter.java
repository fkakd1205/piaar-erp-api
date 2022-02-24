package com.piaar_erp.erp_api.domain.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloRouter {
    @GetMapping("/hello")
    public String helloPage(){
        return "/hello.html";
    }
}
