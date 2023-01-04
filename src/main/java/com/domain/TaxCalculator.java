package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "taxCalculator")
public class TaxCalculator {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "CTPC")
    private String CTPC;

    @NotEmpty
    @Column(name = "CZ")
    private String CZ ;

    @NotNull
    @Column(name ="BasicSalary")
    private Integer BasicSalary;

    @NotNull
    @Column(name ="HouseRent")
    private Integer HouseRent;

    @NotNull
    @Column(name ="MedicalAllowance")
    private Integer MedicalAllowance;

    @NotNull
    @Column(name ="CAllowance")
    private Integer CAllowance;

    @NotNull
    @Column(name ="Bonus")
    private Integer Bonus;

    @NotNull
    @Column(name ="IAR")
    private  Integer IAR;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCTPC() {
        return CTPC;
    }

    public void setCTPC(String CTPC) {
        this.CTPC = CTPC;
    }

    public String getCZ() {
        return CZ;
    }

    public void setCZ(String CZ) {
        this.CZ = CZ;
    }

    public Integer getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        BasicSalary = basicSalary;
    }

    public Integer getHouseRent() {
        return HouseRent;
    }

    public void setHouseRent(Integer houseRent) {
        HouseRent = houseRent;
    }

    public Integer getMedicalAllowance() {
        return MedicalAllowance;
    }

    public void setMedicalAllowance(Integer medicalAllowance) {
        MedicalAllowance = medicalAllowance;
    }

    public Integer getCAllowance() {
        return CAllowance;
    }

    public void setCAllowance(Integer CAllowance) {
        this.CAllowance = CAllowance;
    }

    public Integer getBonus() {
        return Bonus;
    }

    public void setBonus(Integer bonus) {
        Bonus = bonus;
    }

    public Integer getIAR() {
        return IAR;
    }

    public void setIAR(Integer IAR) {
        this.IAR = IAR;
    }
}
