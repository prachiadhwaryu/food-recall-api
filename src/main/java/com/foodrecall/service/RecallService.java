package com.foodrecall.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foodrecall.model.FoodRecall;

@Service
public class RecallService {

    private final List<FoodRecall> recallList = new ArrayList<>(Arrays.asList(
            new FoodRecall(1L, "Peanut Butter", "Brand A", "Salmonella contamination", LocalDate.of(2024, 5, 15)),
            new FoodRecall(2L, "Oats Milk", "Silk", "Listeria", LocalDate.of(2024, 6, 20))));

    public List<FoodRecall> getAllRecalls() {
        return recallList;
    }

    public void addRecall(FoodRecall foodRecall) {
        recallList.add(foodRecall);
    }

    public void deleteRecall(Long id) {
        recallList.removeIf(recall -> recall.getId().equals(id));
    }

    public void updateRecall(Long id, FoodRecall updatedRecall) {
        recallList.replaceAll(recall -> {
            if (recall.getId().equals(id)) {
                return updatedRecall;
            } else {
                return recall;
            }
        });
    }
}
