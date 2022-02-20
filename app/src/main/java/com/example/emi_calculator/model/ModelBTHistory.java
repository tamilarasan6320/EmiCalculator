package com.example.emi_calculator.model;
import java.io.Serializable;
public class ModelBTHistory implements Serializable {

    /* renamed from: A */
    long f3628A;

    /* renamed from: B */
    long f3629B;

    /* renamed from: a */
    int f3630a;

    /* renamed from: b */
    String f3631b;

    /* renamed from: c */
    String f3632c;

    /* renamed from: d */
    String f3633d;

    /* renamed from: e */
    int f3634e;

    /* renamed from: f */
    int f3635f;

    /* renamed from: g */
    long f3636g;

    /* renamed from: h */
    long f3637h;

    /* renamed from: i */
    long f3638i;

    /* renamed from: j */
    double f3639j;

    /* renamed from: k */
    long f3640k;

    /* renamed from: l */
    double f3641l;

    /* renamed from: m */
    long f3642m;

    /* renamed from: n */
    long f3643n;

    /* renamed from: o */
    double f3644o;

    /* renamed from: p */
    long f3645p;

    /* renamed from: q */
    double f3646q;

    /* renamed from: r */
    String f3647r;

    /* renamed from: s */
    long f3648s;

    /* renamed from: t */
    long f3649t;

    /* renamed from: u */
    double f3650u;

    /* renamed from: v */
    String f3651v;

    /* renamed from: w */
    long f3652w;

    /* renamed from: x */
    long f3653x;

    /* renamed from: y */
    long f3654y;

    /* renamed from: z */
    long f3655z;

    public int getBTBankSegmentID() {
        return this.f3635f;
    }

    public int getBTCompanyID() {
        return this.f3634e;
    }

    public long getBTEMIAmount() {
        return this.f3655z;
    }

    public long getBTHistoryID() {
        return (long) this.f3630a;
    }

    public long getBTLoanAmount() {
        return this.f3640k;
    }

    public double getBTLoanROI() {
        return this.f3641l;
    }

    public long getBTLoanTenure() {
        return this.f3642m;
    }

    public String getCustomerMobile() {
        return this.f3632c;
    }

    public String getCustomerName() {
        return this.f3631b;
    }

    public String getCustomerReference() {
        return this.f3633d;
    }

    public long getEMIPaid() {
        return this.f3638i;
    }

    public double getInsurance() {
        return this.f3650u;
    }

    public long getInsuranceAmount() {
        return this.f3652w;
    }

    public long getInsuranceAmountwithGST() {
        return this.f3653x;
    }

    public String getInsuranceType() {
        return this.f3651v;
    }

    public double getMultiplier() {
        return this.f3639j;
    }

    public long getOutstandingAmount() {
        return this.f3637h;
    }

    public double getProcessingFee() {
        return this.f3646q;
    }

    public long getProcessingFeeAmount() {
        return this.f3648s;
    }

    public long getProcessingFeeAmountwithGST() {
        return this.f3649t;
    }

    public String getProcessingFeeType() {
        return this.f3647r;
    }

    public long getSanctionedAmount() {
        return this.f3636g;
    }

    public long getTopupEMIAmount() {
        return this.f3628A;
    }

    public long getTopupEMIAmountwithInsurance() {
        return this.f3629B;
    }

    public long getTopupLoanAmount() {
        return this.f3643n;
    }

    public long getTopupLoanAmountwithInsurance() {
        return this.f3654y;
    }

    public double getTopupLoanROI() {
        return this.f3644o;
    }

    public long getTopupLoanTenure() {
        return this.f3645p;
    }

    public void setBTBankSegmentID(int i) {
        this.f3635f = i;
    }

    public void setBTCompanyID(int i) {
        this.f3634e = i;
    }

    public void setBTEMIAmount(long j) {
        this.f3655z = j;
    }

    public void setBTHistoryID(int i) {
        this.f3630a = i;
    }

    public void setBTLoanAmount(long j) {
        this.f3640k = j;
    }

    public void setBTLoanROI(double d) {
        this.f3641l = d;
    }

    public void setBTLoanTenure(long j) {
        this.f3642m = j;
    }

    public void setCustomerMobile(String str) {
        this.f3632c = str;
    }

    public void setCustomerName(String str) {
        this.f3631b = str;
    }

    public void setCustomerReference(String str) {
        this.f3633d = str;
    }

    public void setEMIPaid(long j) {
        this.f3638i = j;
    }

    public void setInsurance(double d) {
        this.f3650u = d;
    }

    public void setInsuranceAmount(long j) {
        this.f3652w = j;
    }

    public void setInsuranceAmountwithGST(long j) {
        this.f3653x = j;
    }

    public void setInsuranceType(String str) {
        this.f3651v = str;
    }

    public void setMultiplier(double d) {
        this.f3639j = d;
    }

    public void setOutstandingAmount(long j) {
        this.f3637h = j;
    }

    public void setProcessingFee(double d) {
        this.f3646q = d;
    }

    public void setProcessingFeeAmount(long j) {
        this.f3648s = j;
    }

    public void setProcessingFeeAmountwithGST(long j) {
        this.f3649t = j;
    }

    public void setProcessingFeeType(String str) {
        this.f3647r = str;
    }

    public void setSanctionedAmount(long j) {
        this.f3636g = j;
    }

    public void setTopupEMIAmount(long j) {
        this.f3628A = j;
    }

    public void setTopupEMIAmountwithInsurance(long j) {
        this.f3629B = j;
    }

    public void setTopupLoanAmount(long j) {
        this.f3643n = j;
    }

    public void setTopupLoanAmountwithInsurance(long j) {
        this.f3654y = j;
    }

    public void setTopupLoanROI(double d) {
        this.f3644o = d;
    }

    public void setTopupLoanTenure(long j) {
        this.f3645p = j;
    }
}
