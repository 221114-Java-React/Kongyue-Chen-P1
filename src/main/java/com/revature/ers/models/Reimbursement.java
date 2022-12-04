package com.revature.ers.models;


import java.sql.Time;

public class Reimbursement {
    private String id;
    private double amount;
    private Time submitted;
    private Time resolved;
    private String description;
    private String payment_id;
    private String author_id;
    private String resolver_id;
    private String status_id;
    private String type_id;


    public Reimbursement() {
        super();
    }

    public Reimbursement(String id, double amount, Time submitted, Time resolved, String description,  String payment_id, String author_id, String resolver_id, String status_id, String type_id) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.payment_id = payment_id;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Time getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Time submitted) {
        this.submitted = submitted;
    }

    public Time getResolved() {
        return resolved;
    }

    public void setResolved(Time resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(String resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }
}
