package fooddelivery.repository;

import fooddelivery.config.DatabaseConfiguration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryOrders_Products {

    private static RepositoryOrders_Products repositoryOrders_Products = null;

    private RepositoryOrders_Products() {}

    public static RepositoryOrders_Products getInstance(){
        if(repositoryOrders_Products == null)
            repositoryOrders_Products = new RepositoryOrders_Products();
        return repositoryOrders_Products;
    }

    public void insert(int orderId, int productId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        String insertStatement = "INSERT INTO ORDERS_PRODUCTS (orderId, productId) VALUES (?, ?)";

        try{

            CallableStatement cstmt = connection.prepareCall(insertStatement);

            cstmt.setInt(1, orderId);
            cstmt.setInt(2, productId);

            cstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet selectByOrderId(int orderId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM ORDERS_PRODUCTS WHERE orderId = ?";

        try{

            CallableStatement cstmt = connection.prepareCall(selectStatement);
            cstmt.setInt(1, orderId);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
