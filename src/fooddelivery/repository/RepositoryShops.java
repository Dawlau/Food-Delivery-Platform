package fooddelivery.repository;

import fooddelivery.config.DatabaseConfiguration;
import fooddelivery.enums.ProductType;
import fooddelivery.models.Shop;

import javax.xml.transform.Result;
import java.sql.*;

public class RepositoryShops {

    private static RepositoryShops repositoryShops = null;

    private RepositoryShops() {}

    public static RepositoryShops getInstance(){
        if(repositoryShops == null)
            repositoryShops = new RepositoryShops();
        return repositoryShops;
    }

    public void insert(Shop shop){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String insertStatement = "INSERT INTO SHOPS (name, phoneNumber, address, rating) VALUES (?, ?, ?, ?)";

        try{

            CallableStatement cstmt = connection.prepareCall(insertStatement);

            cstmt.setString(1, shop.getName());
            cstmt.setString(2, shop.getPhoneNumber());
            cstmt.setString(3, shop.getAddress());
            cstmt.setDouble(4, shop.getRating());

            cstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet selectALl(){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM SHOPS";

        try{

            CallableStatement cstmt = connection.prepareCall(selectStatement);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet selectById(int shopId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM SHOPS WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            cstmt.setInt(1, shopId);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet selectByName(String shopName){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM SHOPS WHERE name = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            cstmt.setString(1, shopName);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void update(int shopId, Shop shop){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String updateStatement = "UPDATE SHOPS SET name = ?, phoneNumber = ?, address = ?, rating = ? WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(updateStatement);

            cstmt.setString(1, shop.getName());
            cstmt.setString(2, shop.getPhoneNumber());
            cstmt.setString(3, shop.getAddress());
            cstmt.setDouble(4, shop.getRating());
            cstmt.setInt(5, shopId);

            int rowsUpdated = cstmt.executeUpdate();

            if(rowsUpdated > 0){
                System.out.println("Updated shop with id " + shopId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int shopId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String deleteStatement = "DELETE FROM SHOPS WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(deleteStatement);
            cstmt.setInt(1, shopId);

            int rowsUpdated = cstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Deleted shop with id: " + shopId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
