package com.example.anotherone.model;

public class Info {
    String Name;
    String Email;
    String Password;
    String ELPAID;
    String WaterID;
    String ELPAStatus;
    String WaterStatus;
    String CBENumber;
    String CBEPassword;

    public Info(){

    }

    public Info(String ELPAStatus, String waterStatus) {
        this.ELPAStatus = ELPAStatus;
        WaterStatus = waterStatus;
    }

    public Info(String name, String email, String password, String ELPAID, String waterID, String ELPAStatus, String waterStatus, String CBENumber, String CBEPassword) {
        Name = name;
        Email = email;
        Password = password;
        this.ELPAID = ELPAID;
        WaterID = waterID;
        this.ELPAStatus = ELPAStatus;
        WaterStatus = waterStatus;
        this.CBENumber = CBENumber;
        this.CBEPassword = CBEPassword;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getELPAID() {
        return ELPAID;
    }

    public void setELPAID(String ELPAID) {
        this.ELPAID = ELPAID;
    }

    public String getWaterID() {
        return WaterID;
    }

    public void setWaterID(String waterID) {
        WaterID = waterID;
    }

    public String getELPAStatus() {
        return ELPAStatus;
    }

    public void setELPAStatus(String ELPAStatus) {
        this.ELPAStatus = ELPAStatus;
    }

    public String getWaterStatus() {
        return WaterStatus;
    }

    public void setWaterStatus(String waterStatus) {
        WaterStatus = waterStatus;
    }

    public String getCBENumber() {
        return CBENumber;
    }

    public void setCBENumber(String CBENumber) {
        this.CBENumber = CBENumber;
    }

    public String getCBEPassword() {
        return CBEPassword;
    }

    public void setCBEPassword(String CBEPassword) {
        this.CBEPassword = CBEPassword;
    }
}
