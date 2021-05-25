package fooddelivery.repository;

import fooddelivery.config.DatabaseConfiguration;
import fooddelivery.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryShopMenus {

    private static RepositoryShopMenus repositoryShopMenus = null;

    private RepositoryShopMenus() {}

    public static RepositoryShopMenus getInstance(){
        if(repositoryShopMenus == null)
            repositoryShopMenus = new RepositoryShopMenus();
        return repositoryShopMenus;
    }

    public void insert(int shopId, int productId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String insertStatement = "INSERT INTO SHOPMENUS (shopId, productId) VALUES (?, ?)";

        try{

            CallableStatement cstmt = connection.prepareCall(insertStatement);

            cstmt.setInt(1, shopId);
            cstmt.setInt(2, productId);

            cstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet selectByShopId(int shopId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM SHOPMENUS WHERE shopId = ?";

        try{

            CallableStatement cstmt = connection.prepareCall(selectStatement);
            cstmt.setInt(1, shopId);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
