package com.foodrecall;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class RecallController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Food Recall API is up and running!";
    }
}