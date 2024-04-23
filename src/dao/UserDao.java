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

    public User findByLogin(String username, String password, String usertType) {
        User obj = null;
        String query = "SELECT * FROM public.tbl_user WHERE username = ? AND password = ? AND usertype = ?";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            pr.setString(3, usertType);
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
        String sql = "SELECT * FROM public.tbl_user";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
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
        String query = "SELECT * FROM tbl_user WHERE id=" + id;
        try (PreparedStatement ps = DBConnector.getPreparedStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = match(rs);
            }
            //rs.close();
            //ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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
