// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023
package com.example.lostfoundapp.model;

public class Advert {
    private int advert_id;
    private String selectedOption;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;
    private Double cLatitude;
    private Double cLongitude;

    // Constructor to create an Advert object with all fields set
    public Advert(String selectedOption, String name, String phone, String description, String date, Double cLatitude, Double CLongitude) {
        this.selectedOption = selectedOption;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.cLatitude = cLatitude;
        this.cLongitude = CLongitude;
    }

    public Advert() {}

    // Getter for advert_id
    public int getAdvert_id() {
        return advert_id;
    }

    // Setter for advert_id
    public void setAdvert_id(int user_id) {
        this.advert_id = advert_id;
    }

    // Getter for selectedOption
    public String getSelectedOption() {
        return selectedOption;
    }

    // Setter for selectedOption
    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for phone
    public String getPhone() { return phone; }

    // Setter for phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter for description
    public String getDescription() { return description; }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for date
    public String getDate() { return date; }

    // Setter for date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter for location
    public String getLocation() { return location; }

    // Setter for location
    public void setLocation(String location) {
        this.location = location;
    }

    // Getter for latitude
    public Double getLatitude() {
        return cLatitude;
    }

    // Setter for latitude
    public void setLatitude(double latitude) {
        this.cLatitude = latitude;
    }

    // Getter for longitude
    public Double getLongitude() {
        return cLongitude;
    }

    // Setter for longitude
    public void setLongitude(double longitude) {
        this.cLongitude = longitude;
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023