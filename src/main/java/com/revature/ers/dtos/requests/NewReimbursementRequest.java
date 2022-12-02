package com.revature.ers.dtos.requests;

public class NewReimbursementRequest {
    private double amount;
    private String description;
    private String type_id;

    public NewReimbursementRequest() {
        super();
    }

    public NewReimbursementRequest(double amount, String description, String type_id) {
        this.amount = amount;
        this.description = description;
        this.type_id = type_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "NewReimbursementRequest{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}

