package view;

import javax.swing.*;

import business.EmployeeManager;
import business.LoginManager;
import core.Helper;
import entity.Reservation;
import dao.LoginDao;
import entity.*;

import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class EmployeeGUI extends Layout {
    private final EmployeeManager employeeManager = new EmployeeManager();
    private JTabbedPane tabbedPane1;
    private User employee;
    private JPanel pnl_hotel;
    private JTable tbl_hotel;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phoneNumber;
    private JButton btn_addHotel;
    private JComboBox cmb_hotel_star;
    private JPanel pnl_bottom;
    private JTextField fld_city;
    private JTextField fld_startDate;
    private JTextField fld_endDate;
    private JComboBox cmb_childNumber;
    private JComboBox cmb_adultNumber;
    private JButton btn_search;
    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    private JPopupMenu tbl_hotel_PopupMenu;
    private JTable tbl_search;
    private JTextArea fld_hotelFeatures;
    private JTextArea fld_roomFeatures;
    DefaultTableModel mdl_search;
    private Object[] row_search;

    private JTable tbl_rezervations;
    private JTextField fld_addcity;
    private JTextField fld_region;
    private JTextField fld_address;
    private JPanel container;
    private JButton btn_newUserButton;
    DefaultTableModel mdl_rezervations;
    private Object[] row_rezervations;
    private Hotel hotelUpdate;

    public EmployeeGUI(User employee) {
        this.employee = employee;
        add(container);
        guiInitialize(1500, 600);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LoginGUI backToLogin = new LoginGUI();
            }
        });

        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_list = {"ID", "Hotel Name", "City", "Region", "Address", "Email", "Phone Number", "Stars"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        tbl_hotel.setModel(mdl_hotel_list);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);
        tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tbl_hotel.setRowSelectionInterval(tbl_hotel.rowAtPoint(e.getPoint()), tbl_hotel.rowAtPoint(e.getPoint()));
            }
        });
        // After selecting the relevant hotel and clicking, the employee will be able to perform hotel-related operations
        tbl_hotel_PopupMenu = new JPopupMenu();
        tbl_hotel_PopupMenu.add("Manage").addActionListener(e -> {
            int selectedHotelID = Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0).toString());
            HotelDetailGUI detailGUI = new HotelDetailGUI(employeeManager.getHotelByID(selectedHotelID));
        });
        // update hotel knowledge
        tbl_hotel_PopupMenu.add("Update").addActionListener(e -> {
            int selectedHotelID = this.getTableSelectedRow(tbl_hotel, 0);
            Hotel selectedHotel = this.employeeManager.getHotelByID(selectedHotelID);
            setUpdateHotel(selectedHotel);
            this.fld_hotel_name.setText(selectedHotel.getHotelName());
            this.fld_addcity.setText(selectedHotel.getCity());
            this.fld_region.setText(selectedHotel.getRegion());
            this.fld_address.setText(selectedHotel.getAddress());
            this.fld_hotel_email.setText(selectedHotel.getHotelEmail());
            this.fld_hotel_phoneNumber.setText(selectedHotel.getHotelPhoneNumber());

            this.btn_addHotel.setText("Update");
        });
        tbl_hotel_PopupMenu.add("Delete").addActionListener(e -> {
            if (employeeManager.deleteHotel(Integer.parseInt(tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0).toString()))) {
                loadHotelTable();
                Helper.showMessage("done");
            }
        });
        tbl_hotel.setComponentPopupMenu(tbl_hotel_PopupMenu);
        loadHotelTable();
        // create list of reservation table
        mdl_rezervations = new DefaultTableModel();
        mdl_rezervations.setColumnIdentifiers(new Object[]{"Reservation ID", "Hotel ID", "Hotel", "Room ID", "Name", "TC. No", "Phone Number", "Email", "Child Count", "Adult Count"});
        row_rezervations = new Object[10];
        tbl_rezervations.setModel(mdl_rezervations);
        tbl_rezervations.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tbl_rezervations.setRowSelectionInterval(tbl_rezervations.rowAtPoint(e.getPoint()), tbl_rezervations.rowAtPoint(e.getPoint()));
            }
        });
        // Update reservation knowledge

        JPopupMenu tbl_rezervations_popup = new JPopupMenu();
        tbl_rezervations_popup.add("Update").addActionListener(e -> {
            // Take choose of user's reservation col index
            int selectedRow = tbl_rezervations.getSelectedRow();
            if (selectedRow != -1) {
                // Take user's reservation index
                int reservationID = Integer.parseInt(tbl_rezervations.getValueAt(selectedRow, 0).toString());

                // Take data from users
                String customerName = (String) tbl_rezervations.getValueAt(selectedRow, 4);
                String customerTc = (String) tbl_rezervations.getValueAt(selectedRow, 5);
                String customerPhone = (String) tbl_rezervations.getValueAt(selectedRow, 6);
                String customerEmail = (String) tbl_rezervations.getValueAt(selectedRow, 7);
                int childNumber = Integer.parseInt(tbl_rezervations.getValueAt(selectedRow, 8).toString());
                int adultNumber = Integer.parseInt(tbl_rezervations.getValueAt(selectedRow, 9).toString());

                // Call update
                if (employeeManager.updateReservation(reservationID, customerName, customerTc, customerPhone, customerEmail, childNumber, adultNumber)) {
                    // If update procesess is succesfull, it update list of reservation
                    loadRezervationList();
                    Helper.showMessage("Reservation updated successfully.");
                } else {
                    Helper.showMessage("Error occurred while updating reservation.");
                }
            } else {
                Helper.showMessage("Please select a row to update.");
            }
        });
        tbl_rezervations_popup.add("Delete").addActionListener(e -> {
            if (employeeManager.deleteReservation(Integer.parseInt(tbl_rezervations.getValueAt(tbl_rezervations.getSelectedRow(), 0).toString()))) {
                employeeManager.increaseStock(Integer.parseInt(tbl_rezervations.getValueAt(tbl_rezervations.getSelectedRow(), 3).toString()));
                loadRezervationList();
                Helper.showMessage("done");
            }
        });
        tbl_rezervations.setComponentPopupMenu(tbl_rezervations_popup);
        loadRezervationList();
        // List of search result
        mdl_search = new DefaultTableModel();
        mdl_search.setColumnIdentifiers(new Object[]{"Season", "Season Start Date", "Season Finish Date", "Hotel", "City", "District", "Address",
                "Email", "Phone Number", "Stars", "Pension", "Room Type", "Bed Count", "Available Room", "Child Price", "Adult Price", "Hotel ID", "Room ID"});
        row_search = new Object[18];
        tbl_search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tbl_search.setRowSelectionInterval(tbl_search.rowAtPoint(e.getPoint()), tbl_search.rowAtPoint(e.getPoint()));
                int hotelID = Integer.parseInt(tbl_search.getValueAt(tbl_search.getSelectedRow(), 16).toString());
                int roomID = Integer.parseInt(tbl_search.getValueAt(tbl_search.getSelectedRow(), 17).toString());
                loadHotelFeatures(fld_hotelFeatures, employeeManager.getHotelFeatures(hotelID));
                loadRoomFeatures(fld_roomFeatures, employeeManager.getRoomFeatures(roomID));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                tbl_search.setRowSelectionInterval(tbl_search.rowAtPoint(e.getPoint()), tbl_search.rowAtPoint(e.getPoint()));
            }
        });
        tbl_search.setModel(mdl_search);
        tbl_search.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_search.getColumnModel().getColumn(16).setPreferredWidth(0);
        tbl_search.getColumnModel().getColumn(17).setPreferredWidth(0);
        JPopupMenu reservation = new JPopupMenu();

        reservation.add("Reservation").addActionListener(e -> {
            int adult_number = Integer.parseInt((String) cmb_adultNumber.getSelectedItem());
            int child_number = Integer.parseInt((String) cmb_childNumber.getSelectedItem());
            int child_price = Integer.parseInt(tbl_search.getValueAt(tbl_search.getSelectedRow(), 14).toString());
            int adult_price = Integer.parseInt(tbl_search.getValueAt(tbl_search.getSelectedRow(), 15).toString());

            if (fld_startDate.getText().isEmpty() || fld_endDate.getText().isEmpty()) {
                Helper.showMessage("wrongDateRange");
                return;
            }
            int days = employeeManager.calculateDay(fld_startDate.getText(), fld_endDate.getText());
            int totalPrice = (adult_number * days * adult_price) + (child_number * child_price * days);
            ReservationGUI res = new ReservationGUI(
                    this, employeeManager,
                    Integer.parseInt(tbl_search.getValueAt(tbl_search.getSelectedRow(), 16).toString()),
                    Integer.parseInt(tbl_search.getValueAt(tbl_search.getSelectedRow(), 17).toString()),
                    Integer.parseInt(cmb_childNumber.getSelectedItem().toString()),
                    Integer.parseInt(cmb_adultNumber.getSelectedItem().toString()),
                    child_price,
                    adult_price,
                    totalPrice
            );
        });
        tbl_search.setComponentPopupMenu(reservation);
        loadSearchTable();
        btn_addHotel.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_hotel_name) || Helper.isFieldEmpty(fld_hotel_email) || Helper.isFieldEmpty(fld_hotel_phoneNumber)) {
                Helper.showMessage("fill");
            } else {
                if (btn_addHotel.getText().equals("Update")) {
                    employeeManager.updateHotel(
                            hotelUpdate.getHotelID(),
                            fld_hotel_name.getText(),
                            fld_addcity.getText(),
                            fld_region.getText(),
                            fld_address.getText(),
                            fld_hotel_email.getText(),
                            fld_hotel_phoneNumber.getText(),
                            Integer.parseInt((String) cmb_hotel_star.getSelectedItem()));
                    loadHotelTable();
                    Helper.showMessage("done");
                } else {
                    employeeManager.addHotel(
                            fld_hotel_name.getText(),
                            fld_addcity.getText(),
                            fld_region.getText(),
                            fld_address.getText(),
                            fld_hotel_email.getText(),
                            fld_hotel_phoneNumber.getText(),
                            Integer.parseInt((String) cmb_hotel_star.getSelectedItem()));
                    loadHotelTable();
                    Helper.showMessage("done");
                }
            }
        });
        btn_newUserButton.addActionListener(e -> {
            this.fld_hotel_name.setText(null);
            this.fld_addcity.setText(null);
            this.fld_region.setText(null);
            this.fld_address.setText(null);
            this.fld_hotel_email.setText(null);
            this.fld_hotel_phoneNumber.setText(null);
            this.btn_addHotel.setText("Add");
        });
        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!employeeManager.isValidDates(fld_startDate.getText(), fld_endDate.getText())) {
                    Helper.showMessage("wrongDateFormat");
                } else {
                    ArrayList<SearchResult> result = employeeManager.search(
                            employeeManager.searchQuery(
                                    fld_city.getText(),
                                    fld_city.getText(),
                                    fld_city.getText(),
                                    fld_startDate.getText(),
                                    fld_endDate.getText(),
                                    Integer.parseInt(cmb_childNumber.getSelectedItem().toString()) + Integer.parseInt(cmb_adultNumber.getSelectedItem().toString())
                            )
                    );
                    loadSearchTable(result);
                }
            }
        });
    }

    public void loadRezervationList() {
        DefaultTableModel db = (DefaultTableModel) tbl_rezervations.getModel();
        db.setRowCount(0);
        for (Reservation r : employeeManager.getReservationList()) {
            row_rezervations[0] = r.getId();
            row_rezervations[1] = r.getHotelID();
            row_rezervations[2] = employeeManager.getHotelByID(r.getHotelID()).getHotelName();
            row_rezervations[3] = r.getRoomID();
            row_rezervations[4] = r.getCustomerName();
            row_rezervations[5] = r.getCustomerTcNo();
            row_rezervations[6] = r.getCustomerPhone();
            row_rezervations[7] = r.getCustomerEmail();
            row_rezervations[8] = r.getChildNumber();
            row_rezervations[9] = r.getAdultNumber();
            mdl_rezervations.addRow(row_rezervations);
        }
    }

    private void loadHotelFeatures(JTextArea area, ArrayList<HotelFeature> list) {
        area.setText("");
        for (HotelFeature f : list) {
            area.append(f.getFeatureName() + "\n");
        }
    }

    private void loadRoomFeatures(JTextArea area, ArrayList<RoomFeature> list) {
        area.setText("");
        for (RoomFeature r : list) {
            area.append(r.getFeatureName() + "\n");
        }
    }

    private void loadSearchTable(ArrayList<SearchResult> result) {
        DefaultTableModel db = (DefaultTableModel) tbl_search.getModel();
        db.setRowCount(0);
        for (SearchResult r : result) {
            row_search[0] = r.getSeasonName();
            row_search[1] = employeeManager.formatDateBack(r.getSeasonStartDate());
            row_search[2] = employeeManager.formatDateBack(r.getSeasonEndDate());
            row_search[3] = r.getHotelName();
            row_search[4] = r.getHotelCity();
            row_search[5] = r.getHotelRegion();
            row_search[6] = r.getHotelAdress();
            row_search[7] = r.getHotelEmail();
            row_search[8] = r.getHotelPhoneNumber();
            row_search[9] = r.getHotelStars();
            row_search[10] = employeeManager.getPensionNameByID(r.getPensionID());
            row_search[11] = r.getRoomType();
            row_search[12] = r.getRoomBedNumber();
            row_search[13] = r.getRoomStock();
            row_search[14] = r.getRoomPriceChildren();
            row_search[15] = r.getRoomPriceAdult();
            row_search[16] = r.getHotelID();
            row_search[17] = r.getRoomID();
            mdl_search.addRow(row_search);
        }
    }

    private void loadSearchTable() {
        DefaultTableModel db = (DefaultTableModel) tbl_search.getModel();
        db.setRowCount(0);
        for (SearchResult r : employeeManager.search()) {
            row_search[0] = r.getSeasonName();
            row_search[1] = employeeManager.formatDateBack(r.getSeasonStartDate());
            row_search[2] = employeeManager.formatDateBack(r.getSeasonEndDate());
            row_search[3] = r.getHotelName();
            row_search[4] = r.getHotelCity();
            row_search[5] = r.getHotelRegion();
            row_search[6] = r.getHotelAdress();
            row_search[7] = r.getHotelEmail();
            row_search[8] = r.getHotelPhoneNumber();
            row_search[9] = r.getHotelStars();
            row_search[10] = employeeManager.getPensionNameByID(r.getPensionID());
            row_search[11] = r.getRoomType();
            row_search[12] = r.getRoomBedNumber();
            row_search[13] = r.getRoomStock();
            row_search[14] = r.getRoomPriceChildren();
            row_search[15] = r.getRoomPriceAdult();
            row_search[16] = r.getHotelID();
            row_search[17] = r.getRoomID();
            mdl_search.addRow(row_search);
        }
    }

    private void loadHotelTable() {
        DefaultTableModel db = (DefaultTableModel) tbl_hotel.getModel();
        db.setRowCount(0);
        for (Hotel hotel : employeeManager.getHotelList()) {
            row_hotel_list[0] = hotel.getHotelID();
            row_hotel_list[1] = hotel.getHotelName();
            row_hotel_list[2] = hotel.getCity();
            row_hotel_list[3] = hotel.getRegion();
            row_hotel_list[4] = hotel.getAddress();
            row_hotel_list[5] = hotel.getHotelEmail();
            row_hotel_list[6] = hotel.getHotelPhoneNumber();
            row_hotel_list[7] = hotel.getHotelStars();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    private Hotel getUpdateHotel() {
        return hotelUpdate;
    }

    private void setUpdateHotel(Hotel hotel) {
        this.hotelUpdate = hotel;
    }
}
