package fooddelivery.models;

import java.util.ArrayList;

public class Order {

    private int id;
    private String date;
    private String shopName;
    private String courierFirstName;
    private String courierLastName;
    private ArrayList<Product> products;

    public Order(String date, String shopName, String courierFirstName, String courierLastName, ArrayList<Product> products) {
        this.setDate(date);
        this.setShopName(shopName);
        this.setCourierFirstName(courierFirstName);
        this.setCourierLastName(courierLastName);
        this.setProducts(products);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourierFirstName(String courierFirstName) {
        this.courierFirstName = courierFirstName;
    }

    public void setCourierLastName(String courierLastName) {
        this.courierLastName = courierLastName;
    }

    public Order(){
        this("", "", "", "", new ArrayList<>());
    }

    public Order(Order other){
        this(other.date, other.shopName, other.courierFirstName, other.courierLastName, other.products);
    }

    public String getDate() {
        return date;
    }

    public String getShopName() {
        return shopName;
    }


    public ArrayList<Product> getProducts() {
        ArrayList<Product> aux = new ArrayList<>();

        for(Product product : products) {
            aux.add(new Product(product));
        }

        return aux;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = new ArrayList<>();

        for(Product product : products) {
            this.products.add(new Product(product)); // careful with references
        }
    }

    @Override
    public String toString() {
        return "date='" + date + "'" +
                ", shopName='" + shopName + "'" +
                ", courierFirstName='" + courierFirstName + "'" +
                ", courierLastName='" + courierLastName + "'" +
                ", products=" + products;
    }
}
