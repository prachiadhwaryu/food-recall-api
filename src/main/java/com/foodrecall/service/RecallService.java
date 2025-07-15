package com.foodrecall.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.foodrecall.dto.ExternalRecallDTO;
import com.foodrecall.exception.ExternalApiException;
import com.foodrecall.exception.ResourceNotFoundException;
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

    public void deleteRecall(Long id) {
        boolean removed = recallList.removeIf(recall -> recall.getId().equals(id));
        if (!removed) {
            throw new ResourceNotFoundException("Recall with ID " + id + " not found.");
        }
    }

    public FoodRecall updateRecall(Long id, FoodRecall updatedRecall) {
        for (FoodRecall recall : recallList) {
            if (recall.getId().equals(id)) {
                recall.setProductName(updatedRecall.getProductName());
                recall.setBrand(updatedRecall.getBrand());
                recall.setRecallReason(updatedRecall.getRecallReason());
                recall.setRecallDate(updatedRecall.getRecallDate());
                return recall;
            }
        }
        throw new ResourceNotFoundException("Recall with ID " + id + " not found.");
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public List<ExternalRecallDTO> getExternalRecalls() {
        List<ExternalRecallDTO> dtoList = new ArrayList<>();
        try {
            URL url = new URL("https://api.fda.gov/food/enforcement.json?limit=10");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                String response = restTemplate.getForObject(url.toString(), String.class);
                JSONObject json = new JSONObject(response);
                JSONArray results = json.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject obj = results.getJSONObject(i);
                    ExternalRecallDTO dto = new ExternalRecallDTO();
                    dto.setProductDescription(obj.optString("product_description", "N/A"));
                    dto.setReasonForRecall(obj.optString("reason_for_recall", "N/A"));
                    dto.setRecallingFirm(obj.optString("recalling_firm", "N/A"));
                    dto.setRecallInitiationDate(obj.optString("recall_initiation_date", "N/A"));
                    dtoList.add(dto);
                }
            } else {
                throw new ExternalApiException("FDA API returned non-OK status: " + responseCode);
            }
        } catch (IOException e) {
            throw new ExternalApiException("Error calling FDA API: " + e.getMessage());
        }
        return dtoList;
    }
}
