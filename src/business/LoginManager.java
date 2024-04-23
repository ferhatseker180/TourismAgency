package business;

import dao.LoginDao;
import entity.User;
import view.AdminGUI;
import view.EmployeeGUI;

import javax.swing.*;

public class LoginManager {

    private final LoginDao loginDao;

    public LoginManager() {
        this.loginDao = new LoginDao();
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

    }


    public int login(String username, String password, String userRole) {
        return loginDao.login(username, password, userRole);
    }

}
