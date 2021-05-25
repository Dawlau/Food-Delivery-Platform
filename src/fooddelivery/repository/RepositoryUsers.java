package fooddelivery.repository;

import fooddelivery.config.DatabaseConfiguration;
import fooddelivery.models.Product;
import fooddelivery.models.User;

import javax.xml.transform.Result;
import java.sql.*;

public class RepositoryUsers {

    private static RepositoryUsers repositoryUsers = null;

    private RepositoryUsers() {}

    public static RepositoryUsers getInstance(){
        if(repositoryUsers == null)
            repositoryUsers = new RepositoryUsers();
        return repositoryUsers;
    }

    public void insert(User user){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String insertStatement = "INSERT INTO USERS (firstName, lastName, birthday, phoneNumber, email) VALUES (?, ?, ?, ?, ?)";

        try{

            CallableStatement cstmt = connection.prepareCall(insertStatement);

            cstmt.setString(1, user.getFirstName());
            cstmt.setString(2, user.getLastName());
            cstmt.setString(3, user.getBirthday());
            cstmt.setString(4, user.getPhoneNumber());
            cstmt.setString(5, user.getEmail());

            cstmt.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet selectAll(){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM USERS";

        try{

            CallableStatement cstmt = connection.prepareCall(selectStatement);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet selectById(int userId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String selectStatement = "SELECT * FROM USERS WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(selectStatement);

            cstmt.setInt(1, userId);
            return cstmt.executeQuery();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void update(int userId, User user){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String updateStatement = "UPDATE USERS SET firstName = ?, lastName = ?, birthday = ?, phoneNumber = ?, email = ? WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(updateStatement);

            cstmt.setString(1, user.getFirstName());
            cstmt.setString(2, user.getLastName());
            cstmt.setString(3, user.getBirthday());
            cstmt.setString(4, user.getPhoneNumber());
            cstmt.setString(5, user.getEmail());
            cstmt.setInt(6, userId);

            int rowsUpdated = cstmt.executeUpdate();

            if(rowsUpdated > 0){
                System.out.println("Updated user with id " + userId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int userId){

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        String deleteStatement = "DELETE FROM USERS WHERE id = ?";

        try{

            PreparedStatement cstmt = connection.prepareStatement(deleteStatement);
            cstmt.setInt(1, userId);

            int rowsUpdated = cstmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Deleted user with id: " + userId);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
