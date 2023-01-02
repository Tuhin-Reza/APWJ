package com.domain;

import javax.validation.constraints.NotNull;

public class Tax {

    @NotNull(message = "Not Empty")
    private String name;

    private String CTPC;
    private String CZ ;
    @NotNull(message = "Not Empty")
    private int BasicSalary;
    private int HouseRent;
    private int MedicalAllowance;
    private int CAllowance;

    private int Bonus;

    private  int IAR;

    private  int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBasicSalary() {
        return BasicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        BasicSalary = basicSalary;
    }

    public int getHouseRent() {
        return HouseRent;
    }

    public void setHouseRent(int houseRent) {
        HouseRent = houseRent;
    }

    public int getMedicalAllowance() {
        return MedicalAllowance;
    }

    public void setMedicalAllowance(int medicalAllowance) {
        MedicalAllowance = medicalAllowance;
    }

    public int getCAllowance() {
        return CAllowance;
    }

    public void setCAllowance(int CAllowance) {
        this.CAllowance = CAllowance;
    }

    public int getBonus() {
        return Bonus;
    }

    public void setBonus(int bonus) {
        Bonus = bonus;
    }

    public int getIAR() {
        return IAR;
    }

    public void setIAR(int IAR) {
        this.IAR = IAR;
    }


}
