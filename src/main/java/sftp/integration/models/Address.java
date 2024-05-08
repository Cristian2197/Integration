package sftp.integration.models;

import java.util.Map;

public class Address {
    public String address;
    public String city;
    public Coordinates coordinates;
    public String postalCode;
    public String state;

    public Address() { }
    
    public Address(String address, String city, Coordinates coordinates, String postalCode, String state) {
        this.address = address;
        this.city = city;
        this.coordinates = coordinates;
        this.postalCode = postalCode;
        this.state = state;
    }
    public Address(Map<String, Object> addressMapped, Map<String, Object> coordinates) {
        this.address = (String) addressMapped.get("adress");
        this.city = (String) addressMapped.get("city");
        this.coordinates = new Coordinates(coordinates);
        this.postalCode = (String) addressMapped.get("postalCode");
        this.state = (String) addressMapped.get("state");
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    
}