package com.example.e_comerce_admin.models;

public class PurchaseModel {
    private String purchaseId;
    private String productId;
    private String purchaseDate;
    private int purchaseDay;
    private int purchaseMonth;
    private int purchaseYear;
    private double purchasePrice;
    private int purchaseQuantity;

    public PurchaseModel() {
    }

    public PurchaseModel(String purchaseId, String productId, String purchaseDate, int purchaseDay, int purchaseMonth, int purchaseYear, double purchasePrice, int purchaseQuantity) {
        this.purchaseId = purchaseId;
        this.productId = productId;
        this.purchaseDate = purchaseDate;
        this.purchaseDay = purchaseDay;
        this.purchaseMonth = purchaseMonth;
        this.purchaseYear = purchaseYear;
        this.purchasePrice = purchasePrice;
        this.purchaseQuantity = purchaseQuantity;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(int purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public int getPurchaseDay() {
        return purchaseDay;
    }

    public void setPurchaseDay(int purchaseDay) {
        this.purchaseDay = purchaseDay;
    }

    public int getPurchaseMonth() {
        return purchaseMonth;
    }

    public void setPurchaseMonth(int purchaseMonth) {
        this.purchaseMonth = purchaseMonth;
    }

    public int getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(int purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    @Override
    public String toString() {
        return "PurchaseModel{" +
                "purchaseId='" + purchaseId + '\'' +
                ", productId='" + productId + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchaseDay=" + purchaseDay +
                ", purchaseMonth=" + purchaseMonth +
                ", purchaseYear=" + purchaseYear +
                ", purchasePrice=" + purchasePrice +
                ", purchaseQuantity=" + purchaseQuantity +
                '}';
    }
}
