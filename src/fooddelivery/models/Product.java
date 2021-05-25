package fooddelivery.models;
import fooddelivery.enums.ProductType;

import java.util.Objects;

public class Product { // maybe implement filters for price etc

    private int id;
    private double price;
    private String name;
    private String description;
    private ProductType type; // aka FOOD or DRINK

    public Product(){
        this(0, "", "", ProductType.NONE);
    }

    public Product(double price, String name, String description, ProductType type){

        this.setPrice(price);
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(Product other){
        this(other.price, other.name, other.description, other.type);
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductType getType() {
        return type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return  Double.compare(product.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                getType() == product.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getName(), getDescription(), getType());
    }

    @Override
    public String toString() {
        return "NAME: " + name + "       " +
                "DESCRIPTION: " + description + "       " +
                "PRICE: " + price;
    }
}
