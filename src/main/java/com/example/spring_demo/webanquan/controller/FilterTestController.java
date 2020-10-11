package com.example.spring_demo.webanquan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterTestController {


    @GetMapping("/testFilter")
    public String testFilter(String param) {
        return param;
    }

}
