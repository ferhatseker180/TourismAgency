package view;

import business.AdminManager;
import business.LoginManager;
import business.UserManager;
import core.Helper;
import dao.LoginDao;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminGUI extends Layout {
    private JPanel container;
    private JPanel admin_top_pnl;
    private User user;
    private UserManager userManager;
    private JLabel admin_lbl_welcome;
    private JTabbedPane admin_tab_menu;
    private JButton admin_btn_logout;
    private JPanel admin_pnl_worker;
    private JRadioButton filter_admin_rdbtn;
    private JRadioButton filter_employee_rdbtn;
    private JButton admin_filter_btn;
    private JButton admin_clear_btn;
    private JTable admin_tbl_worker;
    private JScrollPane admin_scroll_worker;
    private ButtonGroup rd_filter_button;
    private DefaultTableModel admin_user_table = new DefaultTableModel();
    private JPopupMenu admin_user_menu;
    private Object[] col_user;

    public AdminGUI(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.guiInitialize(500, 500);
        if (this.user == null) {
            dispose();
        }
        rd_filter_button = new ButtonGroup();
        this.add(container);
        this.admin_lbl_welcome.setText("Welcome " + this.user.getName());

        loadUserTable();
        loadUserComponent();
        loadExitComponent();
    }

    private void loadUserComponent() {
        tableRowSelect(this.admin_tbl_worker);
        this.admin_user_menu = new JPopupMenu();
        this.admin_user_menu.add("New").addActionListener(e -> {
            AdminManagementView adminManagementView = new AdminManagementView(new User());
            adminManagementView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        this.admin_filter_btn.addActionListener(e -> {
            if (this.filter_admin_rdbtn.isSelected()) {
                loadFilteredUserTable("admin");
            }
            if (this.filter_employee_rdbtn.isSelected()) {
                loadFilteredUserTable("employee");
            }
        });

        this.admin_clear_btn.addActionListener(e -> {
            this.rd_filter_button.clearSelection();
            loadUserTable();

        });

        this.admin_user_menu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(admin_tbl_worker, 0);
            AdminManagementView adminManagementView = new AdminManagementView(this.userManager.getUserByID(selectUserId));
            adminManagementView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });

        this.admin_user_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(admin_tbl_worker, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMessage("done");
                    loadUserTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.admin_tbl_worker.setComponentPopupMenu(admin_user_menu);
    }

    // It is the code block that provides the filtering process.
    private void loadFilteredUserTable(String userRole) {
        col_user = new Object[]{"ID", "TC", "User Name", "Password", "First Name", "Last Name", "User Role"};
        ArrayList<User> userList = this.userManager.findFilterWorker(userRole);
        ArrayList<Object[]> userObjects = this.userManager.getForTable(col_user.length, userList);
        createTable(this.admin_user_table, this.admin_tbl_worker, col_user, userObjects);
    }

    // It is a function that allows listing all users in a table without filtering.
    public void loadUserTable() {
        col_user = new Object[]{"ID", "TC", "User Name", "Password", "First Name", "Last Name", "User Role"};
        ArrayList<User> userList = this.userManager.findAll();
        ArrayList<Object[]> userObjects = this.userManager.getForTable(col_user.length, userList);
        createTable(this.admin_user_table, this.admin_tbl_worker, col_user, userObjects);
    }

    private void loadExitComponent() {
        this.admin_btn_logout.addActionListener(e -> {
            dispose();
            System.out.println("You are being directed to the Login Page...");
            LoginGUI loginGUI = new LoginGUI();
        });
    }


}
