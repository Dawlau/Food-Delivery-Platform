package ro.localhost.Models;

import java.util.ArrayList;
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

        for(HashMap.Entry<Product, Integer> entry : products.entrySet())
            this.products.put(new Product(entry.getKey()), entry.getValue());
    }

    public HashMap<Product, Integer> getProducts() { // return a copy so it won't modify outside the class
        HashMap<Product, Integer> products = new HashMap<>();

        for(HashMap.Entry<Product, Integer> entry : this.products.entrySet())
            products.put(new Product(entry.getKey()), entry.getValue());

        return products;
    }

    public void listCart(){
        for(Product product : products.keySet())
            for(int i = 0; i < products.get(product); ++i)
                System.out.println(product);
    }

    public void add(Product p){

        if(products.containsKey(p))
            products.put(p, products.get(p) + 1);
        else products.put(p, 1);
    }

    public void remove(Product p){

        if(products.containsKey(p)){
            products.put(p, products.get(p) - 1);

            if(products.get(p) == 0)
                products.remove(p);
        }
        else{
            System.out.println("The product " + p + " does not exist in the cart");
        }
    }
}
