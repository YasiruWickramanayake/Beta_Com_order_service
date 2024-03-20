package com.betacom.coreDomain.entity;

public class OrderDeliveryAddress {
    private final String buildingNumber;
    private final String streetAddress;
    private final String city;
    private final String postalCode;

    public OrderDeliveryAddress(String buildingNumber, String streetAddress, String city, String postalCode) {
        this.buildingNumber = buildingNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "OrderDeliveryAddress{" +
                "buildingNumber='" + buildingNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
