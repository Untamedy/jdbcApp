/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework.flats.entities;

/**
 *
 * @author Lenovo
 */
public class Address {
    
    private String street;
    private int buildNum;

    public Address() {
    }

    public Address(String street, int buildNum) {
        this.street = street;
        this.buildNum = buildNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(int buildNum) {
        this.buildNum = buildNum;
    }

    @Override
    public String toString() {
        return "street=" + street + " " + buildNum;
    }
    
    
    
    
    
}
