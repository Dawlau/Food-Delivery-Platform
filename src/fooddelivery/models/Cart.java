package fooddelivery.models;

import java.util.HashMap;

public class Cart {

    private HashMap<Product, Integer> products;

    public Cart() {
        products = new HashMap<>();
    }

    public Cart(Cart cart){
        this.setProducts(cart.products);
    }

    public void setProducts(HashMap<Product, Integer> products) { // create a copy of what is being set to
        this.products = new HashMap<>();

        for(HashMap.Entry<Product, Integer> entry : products.entrySet()) {
            this.products.put(new Product(entry.getKey()), entry.getValue());
        }
    }

    public HashMap<Product, Integer> getProducts() { // return a copy so it won't modify outside the class
        HashMap<Product, Integer> products = new HashMap<>();

        for(HashMap.Entry<Product, Integer> entry : this.products.entrySet()) {
            products.put(new Product(entry.getKey()), entry.getValue());
        }

        return products;
    }
}
