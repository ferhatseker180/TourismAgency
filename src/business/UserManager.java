package business;

import dao.UserDao;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password, String userType) {
        return userDao.findByLogin(username, password, userType);
    }

    public ArrayList<User> findAll() {
        return userDao.findAll();
    }

    public User getUserByID(int id) {
        return userDao.getUserByID(id);
    }

    public User match(ResultSet rs) {
        User obj = new User();
        try {
            obj.setUserID(rs.getInt("id"));
            obj.setTcNo(rs.getString("tcno"));
            obj.setUsername(rs.getString("username"));
            obj.setPassword(rs.getString("password"));
            obj.setName(rs.getString("name"));
            obj.setSurname(rs.getString("surname"));
            obj.setUserType(rs.getString("usertype"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
