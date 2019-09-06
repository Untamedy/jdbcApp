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
public class Flat {
    
    private int id;
    private String region;
    private Address address;
    private int rooms;    
    private int squeare;
    private double price;
    
    public Flat(){
        
    }

    public Flat(int id, String region, Address address, int rooms, int squeare, double price) {
        this.id = id;
        this.region = region;
        this.address = address;                
        this.rooms = rooms;
        this.squeare = squeare;
        this.price = price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    
    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getSqueare() {
        return squeare;
    }

    public void setSqueare(int squeare) {
        this.squeare = squeare;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "Flat{" + "region=" + region + ", address=" + address.toString() + ", rooms=" + rooms + ", squeare=" + squeare + ", price=" + price + '}';
    }
    
    
    
    
    
}
