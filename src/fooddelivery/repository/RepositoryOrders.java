package fooddelivery.repository;

import fooddelivery.config.DatabaseConfiguration;
import fooddelivery.models.Order;
import fooddelivery.models.Product;

import javax.xml.transform.Result;
import java.sql.*;

public class RepositoryOrders {

    private static RepositoryOrders repositoryOrders = null;

    private RepositoryOrders() {}

    public static RepositoryOrders getInstance(){
        if(repositoryOrders == null)
            repositoryOrders = new RepositoryOrders();
        return repositoryOrders;
    }

    private int getMaxId(){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT MAX(id) FROM ORDERS";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            ResultSet result = cstmt.executeQuery();
            result.next();

            return result.getInt("MAX(id)");

        }catch(SQLException e){
            e.printStackTrace();
        }

        return -1;
    }

    public void insert(Order order, int userId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String insertStatement = "INSERT INTO ORDERS (date, shopName, courierFirstName, courierLastName, userId) VALUES (?, ?, ?, ?, ?)";

        try{

            CallableStatement cstmt = connection.prepareCall(insertStatement);

            cstmt.setString(1, order.getDate());
            cstmt.setString(2, order.getShopName());
            cstmt.setString(3, order.getCourierFirstName());
            cstmt.setString(4, order.getCourierLastName());
            cstmt.setInt(5, userId);

            cstmt.execute();

            int orderId = getMaxId();
            for(Product product : order.getProducts()){

                int productId = product.getId();

                RepositoryOrders_Products repositoryOrders_products = RepositoryOrders_Products.getInstance();
                repositoryOrders_products.insert(orderId, productId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet selectByUserId(int userId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM ORDERS WHERE userId = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            cstmt.setInt(1, userId);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet selectAll(){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM ORDERS";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void update(Order order, Integer userId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String updateStatement = "UPDATE ORDERS SET date = ?, shopName = ?, courierFirstName = ?, courierLastName = ?, userId = ? WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(updateStatement);

            cstmt.setString(1, order.getDate());
            cstmt.setString(2, order.getShopName());
            cstmt.setString(3, order.getCourierFirstName());
            cstmt.setString(4, order.getCourierLastName());
            cstmt.setInt(5, userId);
            cstmt.setInt(6, order.getId());

            int rowsUpdated = cstmt.executeUpdate();

            if(rowsUpdated > 0){
                System.out.println("Updated order with id " + order.getId());
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int orderId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String deleteStatement = "DELETE FROM ORDERS WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(deleteStatement);
            cstmt.setInt(1, orderId);

            int rowsUpdated = cstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Deleted order with id: " + orderId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
