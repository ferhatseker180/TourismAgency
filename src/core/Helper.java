package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setTheme() {
        String theme = "Nimbus";
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (theme.equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static int getLocationPoint(String axis, Dimension size) {
        switch (axis) {
            case "x":
                return (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y":
                return (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default:
                return 0;
        }
    }

    public static boolean isRadioButtonEmpty(JRadioButton radioButton) {
        return radioButton.getText().trim().isEmpty();
    }

    public static boolean isRadioButtonListEmpty(JRadioButton[] radioButtonList) {
        for (JRadioButton radioButton : radioButtonList) {
            if (isRadioButtonEmpty(radioButton)) return true;
        }
        return false;
    }

    public static void showMessage(String str) {
        String message;
        String title;
        switch (str) {
            case "fill":
                message = "Please Fill In The The All Boxes";
                title = "Error!";
                break;
            case "done":
                message = "Processing is Succesful";
                title = "Succesfull!";
                break;
            case "error":
                message = "Wrong Operation!!";
                title = "Error!";
                break;
            case "notFound":
                message = "User Not Found!!";
                title = "Not Found!";
                break;
            case "databaseError":
                message = "Database Error!!";
                title = "Error!";
                break;
            case "wrongPassword":
                message = "Wrong Password!!";
                title = "Error!";
                break;
            case "wrongDateRange":
                message = "Wrong Date Range!!";
                title = "Error!";
                break;
            case "wrongDateFormat":
                message = "Wrong Date Format!!";
                title = "Error!";
                break;
            default:
                message = str;
                title = "Message";
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }


    // Text fld alanlarının boş olup olmadığını kontrol eden metod
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) return true;
        }
        return false;
    }

    public static boolean confirm(String str) {
        String msg;
        switch (str) {
            case "sure":
                msg = "Are you sure you want to perform this action?";
                break;
            default:
                msg = str;
                break;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Are you sure you want to proceed?", JOptionPane.YES_NO_OPTION) == 0;
    }
}
