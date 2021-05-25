package fooddelivery.repository;

import fooddelivery.config.DatabaseConfiguration;
import fooddelivery.enums.ProductType;
import fooddelivery.models.Product;

import javax.xml.transform.Result;
import java.sql.*;

public class RepositoryProducts {

    private static RepositoryProducts repositoryProduct = null;

    private RepositoryProducts() {}

    public static RepositoryProducts getInstance(){
        if(repositoryProduct == null)
            repositoryProduct = new RepositoryProducts();
        return repositoryProduct;
    }

    public void insert(Product product){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        double price = product.getPrice();
        String name = product.getName();
        String description = product.getDescription();
        ProductType type = product.getType();

        String insertStatement = "INSERT INTO PRODUCTS (price, name, description, type) VALUES (?, ?, ?, ?)";

        try{

            CallableStatement cstmt = connection.prepareCall(insertStatement);

            cstmt.setDouble(1, price);
            cstmt.setString(2, name);
            cstmt.setString(3, description);
            cstmt.setString(4, type.toString());

            cstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet selectAll(){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM PRODUCTS";

        try{

            CallableStatement cstmt = connection.prepareCall(selectStatement);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet selectById(int productId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM PRODUCTS WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            cstmt.setInt(1, productId);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet selectByName(String productName){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM PRODUCTS WHERE name = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            cstmt.setString(1, productName);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void update(int productId, Product product){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String updateStatement = "UPDATE PRODUCTS SET price = ?, name = ?, description = ?, type = ? WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(updateStatement);

            cstmt.setDouble(1, product.getPrice());
            cstmt.setString(2, product.getName());
            cstmt.setString(3, product.getDescription());
            cstmt.setString(4, product.getType().toString());
            cstmt.setInt(5, productId);

            int rowsUpdated = cstmt.executeUpdate();

            if(rowsUpdated > 0){
                System.out.println("Updated product with id " + productId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int productId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String deleteStatement = "DELETE FROM PRODUCTS WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(deleteStatement);
            cstmt.setInt(1, productId);

            int rowsUpdated = cstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Deleted product with id: " + productId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
