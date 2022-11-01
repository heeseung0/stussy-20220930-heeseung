package com.stussy.stussyclone20220930heeseung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testcon {

    @GetMapping("tes1")
    public String test1(){
        return "product/product_detail";

    }
    @GetMapping("test2")
    public String test2(){
        return "product/product_order";

    }
}
