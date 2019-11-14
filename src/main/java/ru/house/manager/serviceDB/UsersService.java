package ru.house.manager.serviceDB;
import ru.house.manager.BLDB.Util;
import ru.house.manager.daoDB.UsersDao;
import ru.house.manager.EntityDB.Users;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService extends Util implements UsersDao {

    Connection connection = getConnection();

    @Override
    public void add(Users testUser) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USERS_HM (FIRST_NAME, LAST_NAME, FATHER_NAME, PHONE_NUMBER, E_MAIL, ROOM_NUMBER) VALUES(?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement((sql));
            preparedStatement.setString(1, testUser.getFirstName());
            preparedStatement.setString(2, testUser.getLastName());
            preparedStatement.setString(3, testUser.getFatherName());
            preparedStatement.setString(4, testUser.getPhoneNumber());
            preparedStatement.setString(5, testUser.geteMail());
            preparedStatement.setString(6, testUser.getRoomNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Users> getAll() throws SQLException {
        List<Users> usersList = new ArrayList<>();

        String sql = "SELECT ID, FIRST_NAME, LAST_NAME FROM USERS_HM";
        Statement statement = null;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Users users = new Users();
                users.setId(resultSet.getInt("ID"));
                users.setFirstName(resultSet.getString("FIRST_NAME"));
                users.setLastName(resultSet.getString("LAST_NAME"));
                usersList.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return usersList;
    }

    @Override
    public Users getById(int id) throws SQLException {

        String sql = "SELECT FIRST_NAME, LAST_NAME FROM USERS_HM WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        Users users = new Users();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            users.setFirstName(resultSet.getString("FIRST_NAME"));
            users.setLastName(resultSet.getString("LAST_NAME"));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return users;
    }

    @Override
    public void update(Users user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE USERS_HM SET FIRST_NAME = ?, LAST_NAME = ? WHERE ID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Users user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "DELETE  FROM USERS_HM WHERE ID = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT ID FROM sber_USERS WHERE ID = ( SELECT MAX(ID) FROM SBER_USERS)";
        Statement statement = null;
        int id = -1;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            id = resultSet.getInt("ID");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return id;

    }
}
