package ro.localhost.Services;

import java.awt.*;
import java.util.*;
import java.util.List;

import ro.localhost.DataStructures.Pair;
import ro.localhost.Enums.ProductType;
import ro.localhost.Models.Product;
import ro.localhost.Models.Shop;
import ro.localhost.Models.ShopMenu;

public class ShopsService {

    static final private TreeSet<Shop> shops = new TreeSet<>();

    // Map<ProductName, Product>
    static final private Map<String, Product> products = new HashMap<>();

    // product name, shop name
    static final private ArrayList<Pair<String, String> > shopsAndProducts = new ArrayList<>();

    private static void fetch_Products(){

        csvReader r = csvReader.getInstance();
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

        csvReader r = csvReader.getInstance();
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

        csvReader r = csvReader.getInstance();
        String content = r.readFile("csvFiles/Shops.csv");

        String[] lines = content.split("\n");

        for(int i = 1; i < lines.length; ++i) {
            String[] fields = lines[i].split(",");

            ArrayList<Product> menuProducts = new ArrayList<>();

            for(int j = 0; j < shopsAndProducts.size(); ++j)
                if(shopsAndProducts.get(j).getSecond().equals(fields[0])) {
                    menuProducts.add(products.get(shopsAndProducts.get(j).getFirst()));
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
        for(Shop shop : shops)
            System.out.println(shop.getName());

        ActionTracer.traceAction("All shops were listed");
    }

    public static Shop findByName(String shopName){

        ActionTracer.traceAction("Got request to fetch shop " + shopName);

        for(Shop shop : shops)
            if (shop.getName().toLowerCase().equals(shopName.toLowerCase())) {
                return shop;
            }
        return null;
    }

    public static Product findProductByName(Shop shop, String productName){
        ActionTracer.traceAction("Got request to fetch " + productName + " from " + shop.getName());
        return shop.findProductInMenu(productName);
    }

    public static void listShopMenu(Shop shop){
        ActionTracer.traceAction("Got request to list the menu of " + shop.getName());
        shop.listMenu();
        System.out.println();
    }
}
