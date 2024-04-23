package dao;

import core.DBConnector;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = DBConnector.getConnectionInstance();
    }

    public User findByLogin(String username, String password, String userType) {
        User obj = null;
        String query = "SELECT * FROM tbl_user WHERE username = ? AND password = ? AND usertype = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            pr.setString(3, userType);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM tbl_user";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User getUserByID(int id) {
        User user = null;
        String query = "SELECT * FROM tbl_user WHERE id = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user = match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<User> findFilterWorker(String userRole) {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM tbl_user WHERE usertype = ? ORDER BY id ASC";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, userRole);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean save(User user) {
        String query = "INSERT INTO tbl_user (tcno, username, password, name, surname, usertype) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, user.getTcNo());
            pr.setString(2, user.getUsername());
            pr.setString(3, user.getPassword());
            pr.setString(4, user.getName());
            pr.setString(5, user.getSurname());
            pr.setString(6, user.getUserType());
            return pr.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(User user) {
        String query = "UPDATE tbl_user SET tcno = ?, username = ?, password = ?, name = ?, surname = ?, usertype = ? WHERE id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, user.getTcNo());
            pr.setString(2, user.getUsername());
            pr.setString(3, user.getPassword());
            pr.setString(4, user.getName());
            pr.setString(5, user.getSurname());
            pr.setString(6, user.getUserType());
            pr.setInt(7, user.getUserID());
            return pr.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean delete(int userId) {
        String query = "DELETE FROM tbl_user WHERE id = ?";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setInt(1, userId);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    private User match(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("tcNo"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("usertype"));
    }
}
