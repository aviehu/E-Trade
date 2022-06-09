package com.workshop.ETrade.Domain.Users;

public class SupplyAddress {
    private String city;
    private String street;
    private int streetNum;
    private int apartmentNum;
    private int zip;
    private String country;

    public SupplyAddress(String country,String city, String street, int streetNum, int apartmentNum, int zip) {
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.apartmentNum = apartmentNum;
        this.zip = zip;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        String num = String.valueOf(getStreetNum());
        return street+" "+num;
    }

    public int getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public int getApartmentNum() {
        return apartmentNum;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public void setApartmentNum(int apartmentNum) {
        this.apartmentNum = apartmentNum;
    }
}
