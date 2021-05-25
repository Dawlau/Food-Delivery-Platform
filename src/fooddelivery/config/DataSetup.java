package fooddelivery.config;

import fooddelivery.repository.RepositoryHelper;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSetup {

    public void setUp(){

        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        RepositoryHelper repositoryHelper = RepositoryHelper.getRepositoryHelper();

        String createTableUsers = "CREATE TABLE IF NOT EXISTS USERS" +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "firstName varchar(30)," +
                "lastName varchar(30)," +
                "birthday varchar(30)," +
                "phoneNumber varchar(30)," +
                "email varchar(50))";

        String createTableOrders = "CREATE TABLE IF NOT EXISTS ORDERS" +
                "(id int PRIMARY KEY AUTO_INCREMENT," +
                "date varchar(30)," +
                "shopName varchar(30)," +
                "courierFirstName varchar(30)," +
                "courierLastName varchar(30)" +
                ")";



        String createTableProducts = "CREATE TABLE IF NOT EXISTS PRODUCTS" +
                "(id int PRIMARY KEY AUTO_INCREMENT," +
                "price double," +
                "name varchar(30)," +
                "description varchar(500)," +
                "type varchar(30)" +
                ")";



        String createTableOrders_Products = "CREATE TABLE IF NOT EXISTS ORDERS_PRODUCTS(\n" +
                "   orderId int,\n" +
                "   productId int,\n" +
                "   PRIMARY KEY (orderId, productId),\n" +
                "   CONSTRAINT `Constraint_orderdId_fk`\n" +
                "   FOREIGN KEY orderId_fk (orderId) REFERENCES ORDERS(id)\n" +
                "   ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "   CONSTRAINT `Constraint_productId_fk`\n" +
                "   FOREIGN KEY productId_fk (productId) REFERENCES PRODUCTS(id)\n" +
                "   ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ");";



        String createTableShops = "CREATE TABLE IF NOT EXISTS SHOPS" +
                "(id int PRIMARY KEY AUTO_INCREMENT," +
                "name varchar(30)," +
                "phoneNumber varchar(30)," +
                "address varchar(30)," +
                "rating double)";



        String createTableShopMenus = "CREATE TABLE IF NOT EXISTS SHOPMENUS(\n" +
                "   shopId int,\n" +
                "   productId int,\n" +
                "   PRIMARY KEY (shopId, productId),\n" +
                "   CONSTRAINT `Constraint_shopId_fk`\n" +
                "   FOREIGN KEY shopId_fk (shopId) REFERENCES SHOPS(id)\n" +
                "   ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                "   CONSTRAINT `Constraint_productId_fk1`\n" +
                "   FOREIGN KEY productId_fk1 (productId) REFERENCES PRODUCTS(id)\n" +
                "   ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ");";


        try {
            repositoryHelper.executeSql(databaseConnection, createTableUsers);
            repositoryHelper.executeSql(databaseConnection, createTableOrders);
            repositoryHelper.executeSql(databaseConnection, createTableProducts);
            repositoryHelper.executeSql(databaseConnection, createTableOrders_Products);
            repositoryHelper.executeSql(databaseConnection, createTableShops);
            repositoryHelper.executeSql(databaseConnection, createTableShopMenus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
