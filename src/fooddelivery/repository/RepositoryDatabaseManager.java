package fooddelivery.repository;

import fooddelivery.config.DatabaseConfiguration;
import fooddelivery.enums.ProductType;
import fooddelivery.models.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class RepositoryDatabaseManager {

    private static RepositoryDatabaseManager repositoryDatabaseManager = null;

    private RepositoryDatabaseManager() {}

    public static RepositoryDatabaseManager getInstance(){
        if(repositoryDatabaseManager == null)
            repositoryDatabaseManager = new RepositoryDatabaseManager();
        return repositoryDatabaseManager;
    }

    public void addUsers(ArrayList<User> users){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        RepositoryUsers repositoryUsers = RepositoryUsers.getInstance();
        for (User user : users){
            repositoryUsers.insert(user);
        }
    }

    public ArrayList<User> loadUsers(){

        RepositoryUsers repositoryUsers = RepositoryUsers.getInstance();
        ResultSet allUsers = repositoryUsers.selectAll();
        ArrayList<User> users = new ArrayList<>();

        try{


            while(allUsers.next()){

                User user = new User(
                        allUsers.getString("firstName"),
                        allUsers.getString("lastName"),
                        allUsers.getString("birthday"),
                        allUsers.getString("phoneNumber"),
                        allUsers.getString("email"),
                        new Cart(),
                        new ArrayList<>(),
                        new Order()
                );
                user.setId(allUsers.getInt("id"));
                users.add(user);
            }

            return users;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void addProducts(Map<String, Product> products){

        RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();

        for(Product product : products.values()){
            repositoryProducts.insert(product);
        }
    }

    public Map<String, Product> loadProducts(){

        RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();
        ResultSet allProducts = repositoryProducts.selectAll();

        Map<String, Product> products = new HashMap<>();

        try{

            while(allProducts.next()){

                String type = allProducts.getString("type");
                Product product = new Product(
                        allProducts.getDouble("price"),
                        allProducts.getString("name"),
                        allProducts.getString("description"),
                        (type.equals("DRINK") ? ProductType.DRINK : ProductType.FOOD)
                );
                product.setId(allProducts.getInt("id"));
               products.put(
                       allProducts.getString("name"),
                       product
               );
            }

            return products;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void addShops(TreeSet<Shop> shops){

        RepositoryShops repositoryShops = RepositoryShops.getInstance();

        for(Shop shop : shops){
            repositoryShops.insert(shop);
        }
    }

    public TreeSet<Shop> loadShops(){

        RepositoryShops repositoryShops = RepositoryShops.getInstance();

        ResultSet shopsSet = repositoryShops.selectALl();
        TreeSet<Shop> shops = new TreeSet<>();

        try{

            while(shopsSet.next()){

                // create menus

                ArrayList<Product> products = new ArrayList<>();

                RepositoryShopMenus repositoryShopMenus = RepositoryShopMenus.getInstance();
                ResultSet menus = repositoryShopMenus.selectByShopId(shopsSet.getInt("id"));

                while(menus.next()){

                    int productId = menus.getInt("productId");

                    RepositoryProducts repositoryProducts = RepositoryProducts.getInstance();

                    ResultSet prod = repositoryProducts.selectById(productId);
                    prod.next();

                    String type = prod.getString("type");
                    Product p = new Product(
                            prod.getDouble("price"),
                            prod.getString("name"),
                            prod.getString("description"),
                            (type.equals("DRINK") ? ProductType.DRINK : ProductType.FOOD)
                    );
                    p.setId(prod.getInt("id"));

                    products.add(p);
                }

                Shop shop = new Shop(
                        shopsSet.getString("name"),
                        new ShopMenu(products),
                        shopsSet.getString("phoneNumber"),
                        shopsSet.getString("address"),
                        shopsSet.getDouble("rating")
                );
                shop.setId(shopsSet.getInt("id"));

                shops.add(shop);
            }

            return shops;

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
