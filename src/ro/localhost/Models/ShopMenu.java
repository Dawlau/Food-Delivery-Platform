package ro.localhost.Models;

import java.util.ArrayList;
import java.util.Locale;

public class ShopMenu {

    private ArrayList<Product> products;

    public ShopMenu(){
        this(new ArrayList<>());
    }

    public ShopMenu(ArrayList<Product> products){
        this.setProducts(products);
    }

    public ShopMenu(ShopMenu other){
        this(other.products);
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> aux = new ArrayList<>();

        for(Product product : products)
            aux.add(new Product(product));

        return aux;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = new ArrayList<>();

        for(Product product : products)
            this.products.add(new Product(product));
    }

    public void listMenu(){

        for(int i = 0; i < products.size(); ++i) {
            Product p = products.get(i);
            System.out.println(p);
        }
    }

    public void add(Product p){
        if(!products.contains(p))
            products.add(p);
        else
            System.out.println("Product already in the menu");
    }

    public void remove(Product p){
        if(!products.contains(p))
            System.out.println("Product " + p.getName() + " is not in the menu");
        else products.remove(p);
    }

    public Product findProductByName(String productName){

        for(Product product : products)
            if(product.getName().toLowerCase().equals(productName.toLowerCase())){
                return product;
            }
            return null;
    }

    @Override
    public String toString(){ // for debugging and testing purposes
        StringBuilder repr = new StringBuilder();

        for(Product product : products)
            repr.append(product.getName()).append(", ");
        repr.deleteCharAt(repr.length() - 2);

        return repr.toString();
    }
}
