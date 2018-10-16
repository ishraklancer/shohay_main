package com.shohay.shohay_main;

import android.widget.RatingBar;

public class Order {

    private String userName, userNumber, providerName, providerNumber, providerRate, providerRating, startHour, finishHour, workDate, status = "pending";

    public Order(String userName, String userNumber, String providerName, String providerNumber, String providerRate, String providerRating, String startHour, String finishHour, String workDate, String status) {
        this.userName = userName;
        this.userNumber = userNumber;
        this.providerName = providerName;
        this.providerNumber = providerNumber;
        this.providerRate = providerRate;
        this.providerRating = providerRating;
        this.startHour = startHour;
        this.finishHour = finishHour;
        this.workDate = workDate;
        this.status = status;
    }

    public Order() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(String providerNumber) {
        this.providerNumber = providerNumber;
    }

    public String getProviderRate() {
        return providerRate;
    }

    public void setProviderRate(String providerRate) {
        this.providerRate = providerRate;
    }

    public String getProviderRating() {
        return providerRating;
    }

    public void setProviderRating(String providerRating) {
        this.providerRating = providerRating;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getFinishHour() {
        return finishHour;
    }

    public void setFinishHour(String finishHour) {
        this.finishHour = finishHour;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
