package fooddelivery.models;

import java.util.ArrayList;

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

        for(Product product : products) {
            aux.add(new Product(product));
        }

        return aux;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = new ArrayList<>();

        for(Product product : products) {
            this.products.add(new Product(product));
        }
    }

    @Override
    public String toString(){ // for debugging and testing purposes
        StringBuilder repr = new StringBuilder();

        for(Product product : products) {
            repr.append(product.getName()).append(", ");
        }
        repr.deleteCharAt(repr.length() - 2);

        return repr.toString();
    }
}
