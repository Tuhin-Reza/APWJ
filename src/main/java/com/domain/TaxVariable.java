package com.domain;

public class TaxVariable {
    private int Id;
    private int TIPA;
    private int TaxPayAbleAmount;

    private String TaxPayerCategory;
    private String Zone;
    private int NetTaxAbleIncome;
    private int TaxLiabilityAmount;
    private int TotalPayAbleTaxAmount;
    private int TotalPayAbleTaxAmountM;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTaxPayerCategory() {
        return TaxPayerCategory;
    }

    public void setTaxPayerCategory(String taxPayerCategory) {
        TaxPayerCategory = taxPayerCategory;
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public int getNetTaxAbleIncome() {
        return NetTaxAbleIncome;
    }

    public void setNetTaxAbleIncome(int netTaxAbleIncome) {
        NetTaxAbleIncome = netTaxAbleIncome;
    }

    public int getTaxLiabilityAmount() {
        return TaxLiabilityAmount;
    }

    public void setTaxLiabilityAmount(int taxLiabilityAmount) {
        TaxLiabilityAmount = taxLiabilityAmount;
    }

    public int getTotalPayAbleTaxAmount() {
        return TotalPayAbleTaxAmount;
    }

    public void setTotalPayAbleTaxAmount(int totalPayAbleTaxAmount) {
        TotalPayAbleTaxAmount = totalPayAbleTaxAmount;
    }

    public int getTotalPayAbleTaxAmountM() {
        return TotalPayAbleTaxAmountM;
    }

    public void setTotalPayAbleTaxAmountM(int totalPayAbleTaxAmountM) {
        TotalPayAbleTaxAmountM = totalPayAbleTaxAmountM;
    }

    private int  number;

    public int getTIPA() {
        return TIPA;
    }

    public void setTIPA(int TIPA) {
        this.TIPA = TIPA;
    }

    public int getTaxPayAbleAmount() {
        return TaxPayAbleAmount;
    }

    public void setTaxPayAbleAmount(int taxPayAbleAmount) {
        TaxPayAbleAmount = taxPayAbleAmount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



}
