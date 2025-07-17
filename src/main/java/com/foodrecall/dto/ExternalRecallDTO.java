package com.foodrecall.dto;

public class ExternalRecallDTO {

    private String productDescription;
    private String reasonForRecall;
    private String recallingFirm;
    private String recallInitiationDate;
    private String distributionPattern;

    // Getters and setters

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getReasonForRecall() {
        return reasonForRecall;
    }

    public void setReasonForRecall(String reasonForRecall) {
        this.reasonForRecall = reasonForRecall;
    }

    public String getRecallingFirm() {
        return recallingFirm;
    }

    public void setRecallingFirm(String recallingFirm) {
        this.recallingFirm = recallingFirm;
    }

    public String getRecallInitiationDate() {
        return recallInitiationDate;
    }

    public void setRecallInitiationDate(String recallInitiationDate) {
        this.recallInitiationDate = recallInitiationDate;
    }

    public String getDistributionPattern() {
        return distributionPattern;
    }

    public void setDistributionPattern(String distributionPattern) {
        this.distributionPattern = distributionPattern;
    }
}
