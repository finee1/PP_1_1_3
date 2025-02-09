package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class.getName());
    private Connection conn = Util.getConnection();

    public void createUsersTable() {
        String sql = "CREATE TABLE root.user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100) NOT NULL, lastName VARCHAR(100) NOT NULL, age TINYINT NOT NULL); ";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, sql, e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE user";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, sql, e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, sql, e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE ID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, sql, e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, sql, e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE user";
        try (Statement statement = conn.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, sql, e);
        }
    }
}
