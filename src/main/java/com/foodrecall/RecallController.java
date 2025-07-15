package com.foodrecall;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecall.model.FoodRecall;
import com.foodrecall.service.RecallService;

@RestController
@RequestMapping("/api")

public class RecallController {

    public final RecallService recallService;

    public RecallController(RecallService recallService) {
        this.recallService = recallService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Food Recall API is up and running!";
    }

    @GetMapping("/recalls")
    public List<FoodRecall> getAllRecalls() {
        return recallService.getAllRecalls();
    }

}