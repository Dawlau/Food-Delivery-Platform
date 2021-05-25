package fooddelivery.models;

import fooddelivery.services.ActionTracer;

public class Shop implements Comparable<Shop>{

    private String name;
    private ShopMenu menu;
    private String phoneNumber;
    private String address;
    private double rating;

    @Override
    public int compareTo(Shop shop) {
        return this.name.compareTo(shop.name);
    }

    public Shop(){
        this("", new ShopMenu(), "", "", 0.0);
    }

    public Shop(Shop other){
        this(other.name, other.menu, other.phoneNumber, other.address, other.rating);
    }

    public Shop(String name, ShopMenu menu, String phoneNumber, String address, double rating) {
        this.setName(name);
        this.menu = new ShopMenu(menu);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
        this.setRating(rating);
    }

    public ShopMenu getMenu() {
        return menu;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public double getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() { // for debugging and testing purposes
        return "Shop{" +
                "name='" + name + '\'' +
                ", menu=" + menu +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                '}';
    }
}
