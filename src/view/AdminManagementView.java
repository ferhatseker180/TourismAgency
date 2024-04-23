package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class AdminManagementView extends Layout {
    private JPanel container;
    private JLabel lbl_main_text;
    private JTextField fld_name;
    private JTextField fld_surname;
    private JTextField fld_tc;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JComboBox cmb_user_role;
    private JButton btn_save_user;
    private User user;
    private UserManager userManager;

    public AdminManagementView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(500, 500);

        userRole(this.cmb_user_role);
        checkUserId();
        saveUser();

    }

    public void checkUserId() {
        if (this.user.getUserID() != 0) {
            this.fld_name.setText(this.user.getUsername());
            this.fld_surname.setText(this.user.getSurname());
            this.fld_tc.setText(this.user.getTcNo());
            this.fld_username.setText(this.user.getUsername());
            this.fld_password.setText(this.user.getPassword());
            this.cmb_user_role.setSelectedItem(this.user.getUserType());
        }
    }

    public void saveUser() {
        this.btn_save_user.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_name, this.fld_surname, this.fld_tc, fld_username, fld_password})) {
                Helper.showMessage("fill");
            } else {
                boolean result;

                this.user.setName(this.fld_name.getText());
                this.user.setSurname(this.fld_surname.getText());
                this.user.setTcNo(this.fld_tc.getText());
                this.user.setPassword(new String(this.fld_password.getPassword()));
                this.user.setUserType((String) this.cmb_user_role.getSelectedItem());

                if (this.user.getUserID() != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }

                if (result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
