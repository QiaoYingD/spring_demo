package com.example.spring_demo.nginx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NginxTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getIndex")
    public String getIndex() {
        return "success index port：" + port;
    }


}
