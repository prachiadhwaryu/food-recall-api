package com.foodrecall.model;

import java.time.LocalDate;

public class FoodRecall {

    private Long id;
    private String productName;
    private String brand;
    private String recallReason;
    private LocalDate recallDate;

    public FoodRecall() {
    }

    public FoodRecall(Long id, String productName, String brand, String recallReason, LocalDate recallDate) {
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.recallReason = recallReason;
        this.recallDate = recallDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRecallReason() {
        return recallReason;
    }

    public void setRecallReason(String recallReason) {
        this.recallReason = recallReason;
    }

    public LocalDate getRecallDate() {
        return recallDate;
    }

    public void setRecallDate(LocalDate recallDate) {
        this.recallDate = recallDate;
    }
}
