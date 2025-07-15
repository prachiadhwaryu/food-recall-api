package com.foodrecall.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.foodrecall.dto.ExternalRecallDTO;
import com.foodrecall.model.FoodRecall;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public boolean deleteRecall(Long id) {
        Iterator<FoodRecall> iterator = recallList.iterator();
        while (iterator.hasNext()) {
            FoodRecall recall = iterator.next();
            if (recall.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean updateRecall(Long id, FoodRecall updatedRecall) {
        for (FoodRecall recall : recallList) {
            if (recall.getId().equals(id)) {
                recall.setProductName(updatedRecall.getProductName());
                recall.setBrand(updatedRecall.getBrand());
                recall.setRecallReason(updatedRecall.getRecallReason());
                recall.setRecallDate(updatedRecall.getRecallDate());
                return true;
            }
        }
        return false;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public List<ExternalRecallDTO> getExternalRecalls() {
        String url = "https://api.fda.gov/food/enforcement.json?limit=10";
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);
        JSONArray results = json.getJSONArray("results");

        List<ExternalRecallDTO> dtoList = new ArrayList<>();

        for (int i = 0; i < results.length(); i++) {
            JSONObject obj = results.getJSONObject(i);
            ExternalRecallDTO dto = new ExternalRecallDTO();
            dto.setProductDescription(obj.optString("product_description", "N/A"));
            dto.setReasonForRecall(obj.optString("reason_for_recall", "N/A"));
            dto.setRecallingFirm(obj.optString("recalling_firm", "N/A"));
            dto.setRecallInitiationDate(obj.optString("recall_initiation_date", "N/A"));
            dtoList.add(dto);
        }
        return dtoList;
    }
}
