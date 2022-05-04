package com.example.emi_calculator.model;

public class ROI {
    String category,bank_hfc,lakhs_30_75,lakhs_75;
    public ROI(){

    }

    public ROI(String category, String bank_hfc, String lakhs_30_75, String lakhs_75) {
        this.category = category;
        this.bank_hfc = bank_hfc;
        this.lakhs_30_75 = lakhs_30_75;
        this.lakhs_75 = lakhs_75;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBank_hfc() {
        return bank_hfc;
    }

    public void setBank_hfc(String bank_hfc) {
        this.bank_hfc = bank_hfc;
    }

    public String getLakhs_30_75() {
        return lakhs_30_75;
    }

    public void setLakhs_30_75(String lakhs_30_75) {
        this.lakhs_30_75 = lakhs_30_75;
    }

    public String getLakhs_75() {
        return lakhs_75;
    }

    public void setLakhs_75(String lakhs_75) {
        this.lakhs_75 = lakhs_75;
    }
}
