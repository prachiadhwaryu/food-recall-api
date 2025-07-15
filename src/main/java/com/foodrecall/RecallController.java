package com.foodrecall;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodrecall.dto.ExternalRecallDTO;
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

    // View all recalls
    @GetMapping("/recalls")
    public List<FoodRecall> getAllRecalls() {
        return recallService.getAllRecalls();
    }

    // Add a new recall
    @PostMapping("/recalls")
    public String addRecall(@RequestBody FoodRecall recall) {
        recallService.addRecall(recall);
        return "New recall added successfully!";
    }

    // Delete a recall by ID
    @PostMapping("/recalls/delete")
    public String deleteRecall(@RequestBody Long id) {
        boolean deleted = recallService.deleteRecall(id);
        return deleted ? "Recall with ID " + id + " deleted successfully!" : "Recall not found!";
    }

    // update a recall by ID
    @PutMapping("/recalls/update")
    public String updateRecall(@RequestBody FoodRecall updatedRecall) {
        boolean updated = recallService.updateRecall(updatedRecall.getId(), updatedRecall);
        return updated ? "Recall with ID " + updatedRecall.getId() + " updated successfully!" : "Recall not found!";
    }

    // Fetch external recalls from FDA API
    @GetMapping("/external-recalls")
    public List<ExternalRecallDTO> getExternalRecalls() {
        return recallService.getExternalRecalls();
    }
}