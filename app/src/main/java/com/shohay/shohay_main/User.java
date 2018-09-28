package com.shohay.shohay_main;

public class User {

    private String name;
    private String email;
    private String phone_number;
    private String address;
    private String moila_rate;
    private String gender;
    private String dob;
    private String rating;
    private String dhupi_rate;
    private String napit_rate;

    public User(String name, String email, String phone_number, String address, String moila_rate, String gender, String dob, String rating, String dhupi_rate, String napit_rate) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.moila_rate = moila_rate;
        this.gender = gender;
        this.dob = dob;
        this.rating = rating;
        this.dhupi_rate = dhupi_rate;
        this.napit_rate = napit_rate;
    }


    public User() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public String getMoila_rate() {
        return moila_rate;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getRating() {
        return rating;
    }

    public String getDhupi_rate() {
        return dhupi_rate;
    }

    public String getNapit_rate() {
        return napit_rate;
    }
}
