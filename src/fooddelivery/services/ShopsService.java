package fooddelivery.services;

import java.util.*;

import fooddelivery.dataStructures.Pair;
import fooddelivery.enums.ProductType;
import fooddelivery.models.Product;
import fooddelivery.models.Shop;
import fooddelivery.models.ShopMenu;

public class ShopsService {

    static final private TreeSet<Shop> shops = new TreeSet<>();

    // Map<ProductName, Product>
    static final private Map<String, Product> products = new HashMap<>();

    // product name, shop name
    static final private ArrayList<Pair<String, String>> shopsAndProducts = new ArrayList<>();

    private static void fetch_Products(){

        CsvReader r = CsvReader.getInstance();
        String content = r.readFile("csvFiles/Products.csv");

        String[] lines = content.split("\n");

        for(int i = 1; i < lines.length; ++i){
            String[] fields = lines[i].split(",");

            Product product = new Product(
                    Double.parseDouble(fields[1]), // price
                    fields[0], // name
                    fields[2], // description
                    fields[3].equals("FOOD") ? ProductType.FOOD : ProductType.DRINK // type
            );

            products.put(product.getName(), product);
        }

        ActionTracer.traceAction("Products got fetched successfully");
    }

    private static void fetch_Menus(){

        CsvReader r = CsvReader.getInstance();
        String content = r.readFile("csvFiles/ShopMenus.csv");

        String[] lines = content.split("\n");

        for(int i = 1; i < lines.length; ++i){
            String[] fields = lines[i].split(",");

            String productName = fields[0];
            String shopName = fields[1];

            shopsAndProducts.add(new Pair<>(productName, shopName));
        }

        ActionTracer.traceAction("Menus fetched successfully");
    }

    private static void fetch_Shops(){

        CsvReader r = CsvReader.getInstance();
        String content = r.readFile("csvFiles/Shops.csv");

        String[] lines = content.split("\n");

        for(int i = 1; i < lines.length; ++i) {
            String[] fields = lines[i].split(",");

            ArrayList<Product> menuProducts = new ArrayList<>();

            for (Pair<String, String> shopsAndProduct : shopsAndProducts) {
                if (shopsAndProduct.getSecond().equals(fields[0])) {
                    menuProducts.add(products.get(shopsAndProduct.getFirst()));
                }
            }


            Shop shop = new Shop(
                    fields[0], // name
                    new ShopMenu(menuProducts), // menu
                    fields[1], // phone number
                    fields[2], // address
                    Double.parseDouble(fields[3]) // rating
            );

            shops.add(shop);
        }

        ActionTracer.traceAction("Shops fetched successfully");
    }

    public static void fetch_ShopsData(){ // will read from a file or a database in the future
        fetch_Products();
        fetch_Menus();
        fetch_Shops();
    }

    public static void listShops(){

        System.out.println("The shops available are:");
        for(Shop shop : shops) {
            System.out.println(shop.getName());
        }

        ActionTracer.traceAction("All shops were listed");
    }

    public static Shop findByName(String shopName){

        ActionTracer.traceAction("Got request to fetch shop " + shopName);

        for(Shop shop : shops) {
            if (shop.getName().toLowerCase().equals(shopName.toLowerCase())) {
                return shop;
            }
        }
        return null;
    }

    public static Product findProductByName(Shop shop, String productName){
        ActionTracer.traceAction("Got request to fetch " + productName + " from " + shop.getName());

        for(Product product : shop.getMenu().getProducts()){
            if (product.getName().toLowerCase().equals(productName.toLowerCase())) {
                return product;
            }
        }

        return null;
    }

    public static void listShopMenu(Shop shop){
        ActionTracer.traceAction("Got request to list the menu of " + shop.getName());
        System.out.println("The menu at " + shop.getName() + " is:");

        for(Product product : shop.getMenu().getProducts()){
            System.out.println(product);
        }

        ActionTracer.traceAction(shop.getName() + "'s menu got listed");

        System.out.println();
    }

    public static void removeProductFromMenu(Shop shop, Product product){

        ActionTracer.traceAction("Removed " + product.getName() + " from " + shop.getName() + "'s menu");

        ArrayList<Product> products = shop.getMenu().getProducts();

        if(!products.contains(product)) {
            System.out.println("Product " + product.getName() + " is not in the menu");
        }
        else {
            products.remove(product);
        }
    }

//    public static void addProductToMenu(Shop shop, Product product){
//
//        ArrayList<Product> products = shop.getMenu().getProducts();
//
//        if(!products.contains(product)) {
//            products.add(product);
//            shop.getMenu().setProducts(products);
//
//            ActionTracer.traceAction("Added " + product.getName() + " to " + product.getName() + "'s menu");
//        }
//        else {
//            System.out.println("Product already in the menu");
//        }
//    }
}
