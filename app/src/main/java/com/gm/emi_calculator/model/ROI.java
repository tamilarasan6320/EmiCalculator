package com.gm.emi_calculator.model;

public class ROI {
    String category,bank,interest;
    public ROI(){

    }

    public ROI(String category, String bank, String interest) {
        this.category = category;
        this.bank = bank;
        this.interest = interest;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }
}
