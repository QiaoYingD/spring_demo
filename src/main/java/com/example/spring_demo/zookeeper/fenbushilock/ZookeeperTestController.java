package com.example.spring_demo.zookeeper.fenbushilock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZookeeperTestController {


    @GetMapping("/createNumber")
    private void createNumber() {
        for (int i = 0; i < 100; i++) {
            new Thread(new OrderService()).start();
        }
    }


}
