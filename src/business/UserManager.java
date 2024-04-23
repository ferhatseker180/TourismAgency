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

    public ArrayList<User> findFilterWorker(String userRole) {
        return userDao.findFilterWorker(userRole);
    }

    public boolean save(User user) {
        return userDao.save(user);
    }

    public boolean update(User user) {
        return userDao.update(user);
    }

    public boolean delete(int userId) {
        return userDao.delete(userId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userList = new ArrayList<>();
        for (User obj : users) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getUserID();
            rowObject[i++] = obj.getTcNo();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getSurname();
            rowObject[i++] = obj.getUserType();
            userList.add(rowObject);
        }
        return userList;
    }

}
