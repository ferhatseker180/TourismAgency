package view;

import business.LoginManager;
import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginGUI extends Layout {
    private JPanel container;
    private JRadioButton rd_admin;
    private JRadioButton rd_employee;
    private JTextField login_username_fld;
    private JPasswordField login_password_fld;
    private JButton btn_login;
    private LoginManager loginManager;
    private UserManager userManager;
    private User user;
    String selectedRole;

    public LoginGUI(LoginManager loginManager) {
        this.loginManager = loginManager;
        this.userManager = new UserManager();

        this.add(container);
        this.guiInitialize(500, 500);

        btn_login.addActionListener(e -> {
            JTextField[] checkedList = {this.login_username_fld, this.login_password_fld};
            JRadioButton[] checkedRadioList = {this.rd_admin, this.rd_employee};
            if (Helper.isFieldListEmpty(checkedList) || Helper.isRadioButtonListEmpty(checkedRadioList)) {
                Helper.showMessage("fill");
            } else {
                if (this.rd_admin.isSelected()) {
                    selectedRole = "admin";
                }
                if (this.rd_employee.isSelected()) {
                    selectedRole = "employee";
                }
                User loginUser = this.userManager.findByLogin(this.login_username_fld.getText().trim(), this.login_password_fld.getText().trim(), selectedRole);
                if (loginUser == null) {
                    Helper.showMessage("notFound");
                } else {
                    if (this.rd_admin.isSelected()) {
                        AdminGUI adminGUI = new AdminGUI(loginUser);
                        System.out.println("You are being redirected to the Admin Page...");
                        dispose();
                    }
                    if (this.rd_employee.isSelected()) {
                        EmployeeGUI employeeGUI = new EmployeeGUI(loginUser);
                        System.out.println("You will be redirected to the Worker page...");
                        dispose();
                    }

                }
            }
        });
    }
}