package com.stussy.stussyclone20220930heeseung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testcon {

    @GetMapping("test1")
    public String test(){
        return "product/product_detail.html";

    }
}
