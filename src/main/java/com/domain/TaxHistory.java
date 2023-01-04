package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="taxcalculator")
public class TaxHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "*")
    @Column(name ="username")
    private String username;

    @NotEmpty(message = "*")
    @Column(name ="taxPayerCategory")
    private String taxPayerCategory;

    @NotEmpty(message = "*")
    @Column(name ="zone")
    private String zone;

    @NotNull(message = "*")
    @Column(name ="netTaxAbleIncome")
    private int netTaxAbleIncome;

    @NotNull(message = "*")
    @Column(name ="taxLiabilityAmount")
    private int taxLiabilityAmount;

    @NotNull(message = "*")
    @Column(name ="totalPayAbleTaxAmountM")
    private int totalPayAbleTaxAmountM;

    @NotNull(message = "*")
    @Column(name ="totalPayAbleTaxAmount")
    private int totalPayAbleTaxAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaxPayerCategory() {
        return taxPayerCategory;
    }

    public void setTaxPayerCategory(String taxPayerCategory) {
        this.taxPayerCategory = taxPayerCategory;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getNetTaxAbleIncome() {
        return netTaxAbleIncome;
    }

    public void setNetTaxAbleIncome(int netTaxAbleIncome) {
        this.netTaxAbleIncome = netTaxAbleIncome;
    }

    public int getTaxLiabilityAmount() {
        return taxLiabilityAmount;
    }

    public void setTaxLiabilityAmount(int taxLiabilityAmount) {
        this.taxLiabilityAmount = taxLiabilityAmount;
    }

    public int getTotalPayAbleTaxAmountM() {
        return totalPayAbleTaxAmountM;
    }

    public void setTotalPayAbleTaxAmountM(int totalPayAbleTaxAmountM) {
        this.totalPayAbleTaxAmountM = totalPayAbleTaxAmountM;
    }

    public int getTotalPayAbleTaxAmount() {
        return totalPayAbleTaxAmount;
    }

    public void setTotalPayAbleTaxAmount(int totalPayAbleTaxAmount) {
        this.totalPayAbleTaxAmount = totalPayAbleTaxAmount;
    }
}
