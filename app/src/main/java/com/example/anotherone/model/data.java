package com.example.anotherone.model;

import java.util.concurrent.atomic.AtomicBoolean;

public class data {

    String uid;
    int elpa_usage;
    int water_usage;
    int elpa_last_month;
    int elpa_current_month;
    int water_last_month;
    int water_current_month;


    public data(){

    }

    public data(String uid, int elpa_usage, int water_usage, int elpa_last_month, int elpa_current_month, int water_last_month, int water_current_month) {
        this.uid = uid;
        this.elpa_usage = elpa_usage;
        this.water_usage = water_usage;
        this.elpa_last_month = elpa_last_month;
        this.elpa_current_month = elpa_current_month;
        this.water_last_month = water_last_month;
        this.water_current_month = water_current_month;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getElpa_usage() {
        return elpa_usage;
    }

    public void setElpa_usage(int elpa_usage) {
        this.elpa_usage = elpa_usage;
    }

    public int getWater_usage() {
        return water_usage;
    }

    public void setWater_usage(int water_usage) {
        this.water_usage = water_usage;
    }

    public int getElpa_last_month() {
        return elpa_last_month;
    }

    public void setElpa_last_month(int elpa_last_month) {
        this.elpa_last_month = elpa_last_month;
    }

    public int getElpa_current_month() {
        return elpa_current_month;
    }

    public void setElpa_current_month(int elpa_current_month) {
        this.elpa_current_month = elpa_current_month;
    }

    public int getWater_last_month() {
        return water_last_month;
    }

    public void setWater_last_month(int water_last_month) {
        this.water_last_month = water_last_month;
    }

    public int getWater_current_month() {
        return water_current_month;
    }

    public void setWater_current_month(int water_current_month) {
        this.water_current_month = water_current_month;
    }
}
