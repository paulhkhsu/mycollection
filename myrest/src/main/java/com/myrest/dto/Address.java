package com.myrest.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address extends ContactInfo {
 
    private String street;
 
    public String getStreet() {
        return street;
    }
 
    public void setStreet(String street) {
        this.street = street;
    }
 
}