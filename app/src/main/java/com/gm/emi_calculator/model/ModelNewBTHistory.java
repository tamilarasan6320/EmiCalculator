package com.gm.emi_calculator.model;
import java.io.Serializable;

public class ModelNewBTHistory implements Serializable {
    String Salary,SanctionedAmount,CurrentBalance,EMIPaid,BTAmount,TopupAmount,BTROI,TopupROI,BTTenure,TopupTenure,BTEMI,TopupEMI,Foir;


    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getSanctionedAmount() {
        return SanctionedAmount;
    }

    public void setSanctionedAmount(String sanctionedAmount) {
        SanctionedAmount = sanctionedAmount;
    }

    public String getCurrentBalance() {
        return CurrentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        CurrentBalance = currentBalance;
    }

    public String getEMIPaid() {
        return EMIPaid;
    }

    public void setEMIPaid(String EMIPaid) {
        this.EMIPaid = EMIPaid;
    }

    public String getBTAmount() {
        return BTAmount;
    }

    public void setBTAmount(String BTAmount) {
        this.BTAmount = BTAmount;
    }

    public String getTopupAmount() {
        return TopupAmount;
    }

    public void setTopupAmount(String topupAmount) {
        TopupAmount = topupAmount;
    }

    public String getBTROI() {
        return BTROI;
    }

    public void setBTROI(String BTROI) {
        this.BTROI = BTROI;
    }

    public String getTopupROI() {
        return TopupROI;
    }

    public void setTopupROI(String topupROI) {
        TopupROI = topupROI;
    }

    public String getBTTenure() {
        return BTTenure;
    }

    public void setBTTenure(String BTTenure) {
        this.BTTenure = BTTenure;
    }

    public String getTopupTenure() {
        return TopupTenure;
    }

    public void setTopupTenure(String topupTenure) {
        TopupTenure = topupTenure;
    }

    public String getBTEMI() {
        return BTEMI;
    }

    public void setBTEMI(String BTEMI) {
        this.BTEMI = BTEMI;
    }

    public String getTopupEMI() {
        return TopupEMI;
    }

    public void setTopupEMI(String topupEMI) {
        TopupEMI = topupEMI;
    }

    public String getFoir() {
        return Foir;
    }

    public void setFoir(String foir) {
        Foir = foir;
    }
}
