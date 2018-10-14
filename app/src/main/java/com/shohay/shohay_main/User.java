package com.shohay.shohay_main;

public class User {

    private String name;
    private String email;
    private String phone_number;
    private String address;
    private Double latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public User(String name, String email, String phone_number, String address, Double latitude, Double longitude, String gender, String dob, String rating) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;

        this.gender = gender;
        this.dob = dob;
        this.rating = rating;
    }

    private Double longitude;
    private String gender;
    private String dob;
    private String rating;


}
