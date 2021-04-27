package ro.localhost.Services;

import java.awt.*;
import java.util.ArrayList;
import java.util.TreeSet;

import ro.localhost.Enums.ProductType;
import ro.localhost.Models.Product;
import ro.localhost.Models.Shop;
import ro.localhost.Models.ShopMenu;

public class ShopsService {

    static final private TreeSet<Shop> shops = new TreeSet<>();

    public static void fetch_ShopsData(){ // will read from a file or a database in the future

        Product pizza = new Product(
                25.0,
                "Pizza",
                "This is a delicious pizza",
                ProductType.FOOD
        );

        Product bagel = new Product(
                2.0,
                "Bagel",
                "Simple bagel",
                ProductType.FOOD
        );

        Product sandwich = new Product(
                6.0,
                "Sandwich",
                "Sandwich with cheese and ham",
                ProductType.FOOD
        );

        Product cola = new Product(
                5.0,
                "Cocacola",
                "Classic coca cola",
                ProductType.DRINK
        );

        Product donut = new Product(
                3.0,
                "Donut",
                "Jam donut",
                ProductType.FOOD
        );

        Product sprite = new Product(
                4.5,
                "Sprite",
                "Diet Sprite",
                ProductType.DRINK
        );

        ShopMenu menu = new ShopMenu();
        menu.add(sprite);
        menu.add(cola);
        menu.add(pizza);
        menu.add(bagel);

        ShopMenu lucaMenu = new ShopMenu(menu);

        Shop shop1 = new Shop(
                "Luca",
                lucaMenu,
                "0752411523",
                "Langa facultate",
                4.65
        );

        ShopMenu pizzahutMenu = new ShopMenu(menu);
        pizzahutMenu.remove(bagel);
        pizzahutMenu.add(sandwich);

        Shop shop2 = new Shop(
                "PizzaHut",
                pizzahutMenu,
                "07124231524",
                "Mai jos de facultate",
                4.05
        );

        ShopMenu luca2Menu = new ShopMenu(menu);
        luca2Menu.remove(sprite);


        Shop shop3 = new Shop(
                "CelalaltLuca",
                luca2Menu,
                "07351251264",
                "Langa Luca",
                3.09
        );

        shops.add(shop1);
        shops.add(shop2);
        shops.add(shop3);
    }

    public static void listShops(){

        System.out.println("The shops available are:");
        for(Shop shop : shops)
            System.out.println(shop.getName());
    }

    public static Shop findByName(String shopName){

        for(Shop shop : shops)
            if (shop.getName().equals(shopName)) {
                return shop;
            }
        return null;
    }

    public static Product findProductByName(Shop shop, String productName){
        return shop.getMenu().findProductByName(productName);
    }

    public static void listShopMenu(Shop shop){

        shop.listMenu();
        System.out.println();
    }
}
