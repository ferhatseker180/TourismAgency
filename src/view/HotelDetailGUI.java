package view;

import business.HotelDetailManager;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HotelDetailGUI extends Layout {
    private Hotel hotel;
    private HotelDetailManager detailManager = new HotelDetailManager();
    private JPanel container;
    private JTabbedPane tabbedPane;
    private JTable tbl_season;
    DefaultTableModel mdl_season;
    private Object[] row_season;
    private JTextField fld_season_name;
    private JTextField fld_season_startDate;
    private JTextField fld_season_endDate;
    private JButton btn_addSeason;
    private JButton btn_addPensiontoHotel;
    private JComboBox cmb_pension;
    private JTable tbl_pension;
    private JTable tbl_hotel_feature;
    private DefaultTableModel mdl_hotelFeature;
    private Object[] row_hotelFeature;
    private JComboBox cmb_hotelFeatures;
    private JButton btn_addHotelFeature;
    private JComboBox cmb_room_season;
    private JComboBox cmb_room_pension;
    private JTextField fld_room_type;
    private JTextField fld_room_bedNumber;
    private JTextField fld_room_stock;
    private JTextField fld_room_price_child;
    private JButton btn_addHotelRoom;
    DefaultTableModel mdl_pension;
    private Object[] row_pension;
    private JTable tbl_room;
    private JTextField fld_room_price_adult;
    private JPopupMenu tbl_room_popup;
    DefaultTableModel mdl_room;
    private Object[] row_room;

    public HotelDetailGUI(Hotel hotel) {
        this.hotel = hotel;
        this.add(container);
        guiInitialize(1000, 500);

        // Create Season Table
        mdl_season = new DefaultTableModel();
        // tablonun sütun isimleri
        mdl_season.setColumnIdentifiers(new Object[]{"ID", "Season", "Start Date", "End Date"});
        row_season = new Object[4];
        tbl_season.setModel(mdl_season);
        tbl_season.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tbl_season.setRowSelectionInterval(tbl_season.rowAtPoint(e.getPoint()), tbl_season.rowAtPoint(e.getPoint()));
            }
        });
        JPopupMenu tbl_season_popup = new JPopupMenu();
        // Delete season
        tbl_season_popup.add("Delete").addActionListener(e -> {
            if (detailManager.deleteSeason(Integer.parseInt(tbl_season.getValueAt(tbl_season.getSelectedRow(), 0).toString()))) {
                loadSeasonList();
                Helper.showMessage("done");
            } else {
                Helper.showMessage("error");
            }
        });
        tbl_season.setComponentPopupMenu(tbl_season_popup);
        loadSeasonList();

        // Create Pension Table
        mdl_pension = new DefaultTableModel();
        mdl_pension.setColumnIdentifiers(new Object[]{"ID", "Pension"});
        row_pension = new Object[2];
        tbl_pension.setModel(mdl_pension);
        tbl_pension.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tbl_pension.setRowSelectionInterval(tbl_pension.rowAtPoint(e.getPoint()), tbl_pension.rowAtPoint(e.getPoint()));
            }
        });
        JPopupMenu tbl_pension_popup = new JPopupMenu();
        // Delete Pension
        tbl_pension_popup.add("Delete").addActionListener(e -> {
            if (detailManager.deleteHotelPension(Integer.parseInt(tbl_pension.getValueAt(tbl_pension.getSelectedRow(), 0).toString()))) {
                loadPensionList();
                Helper.showMessage("done");
            } else {
                Helper.showMessage("error");
            }
        });
        tbl_pension.setComponentPopupMenu(tbl_pension_popup);
        loadPensionList();

        // Create Hotel Feature List
        mdl_hotelFeature = new DefaultTableModel();
        mdl_hotelFeature.setColumnIdentifiers(new Object[]{"Feature"});
        row_hotelFeature = new Object[1];
        tbl_hotel_feature.setModel(mdl_hotelFeature);
        tbl_hotel_feature.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tbl_hotel_feature.setRowSelectionInterval(tbl_hotel_feature.rowAtPoint(e.getPoint()), tbl_hotel_feature.rowAtPoint(e.getPoint()));
            }
        });
        JPopupMenu tbl_hotelFeature_popup = new JPopupMenu();
        // Delete Hotel Feature
        tbl_hotelFeature_popup.add("Sil").addActionListener(e -> {
            if (detailManager.deleteHotelFeature(hotel.getHotelID(),
                    tbl_hotel_feature.getValueAt(tbl_hotel_feature.getSelectedRow(), 0).toString())) {
                loadHotelFeatureList();
                Helper.showMessage("done");
            } else {
                Helper.showMessage("error");
            }
        });
        tbl_hotel_feature.setComponentPopupMenu(tbl_hotelFeature_popup);
        loadHotelFeatureList();

        // Create List of hotel
        mdl_room = new DefaultTableModel();
        mdl_room.setColumnIdentifiers(new Object[]{"ID", "Season", "Pension", "Room Type", "Bed Count", "Stock", "Child Price", "Adult Price"});
        row_room = new Object[8];
        tbl_room.setModel(mdl_room);
        loadHotelRoomList();
        tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tbl_room.setRowSelectionInterval(tbl_room.rowAtPoint(e.getPoint()), tbl_room.rowAtPoint(e.getPoint()));
            }
        });
        tbl_room_popup = new JPopupMenu();
        // Add Room Feature
        tbl_room_popup.add("Add Feature").addActionListener(e -> {
            int id = Integer.parseInt(tbl_room.getValueAt(tbl_room.getSelectedRow(), 0).toString());
            AddRoomGUI addRoomProperty = new AddRoomGUI(detailManager, id);
        });

        // Update Room Knowledge
        tbl_room_popup.add("Update").addActionListener(e -> {
            if (tbl_room.getCellEditor() != null) {
                tbl_room.getCellEditor().stopCellEditing();
                if (detailManager.updateRoomInfo(
                        tbl_room.getValueAt(tbl_room.getSelectedRow(), 3).toString(),
                        Integer.parseInt(tbl_room.getValueAt(tbl_room.getSelectedRow(), 4).toString()),
                        Integer.parseInt(tbl_room.getValueAt(tbl_room.getSelectedRow(), 5).toString()),
                        Integer.parseInt(tbl_room.getValueAt(tbl_room.getSelectedRow(), 6).toString()),
                        Integer.parseInt(tbl_room.getValueAt(tbl_room.getSelectedRow(), 7).toString())
                )) {
                    loadHotelRoomList();
                    Helper.showMessage("done");
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        // Delete Room
        tbl_room_popup.add("Delete").addActionListener(e -> {
            if (detailManager.deleteRoom((int) tbl_room.getValueAt(tbl_room.getSelectedRow(), 0))) {
                loadHotelRoomList();
                Helper.showMessage("done");
            }
        });
        tbl_room.setComponentPopupMenu(tbl_room_popup);

        // Call Combobox
        loadPensionCombobox();
        loadHotelFeaturesCombobox();
        loadRoomSeasonCombobox();
        loadRoomPensionCombobox();

        // Add Season For Hotel
        btn_addSeason.addActionListener(e -> {
            if (isValidDates(fld_season_startDate.getText(), fld_season_endDate.getText())) {
                if (detailManager.addSeason(
                        hotel.getHotelID(),
                        fld_season_name.getText(),
                        formatDate(fld_season_startDate.getText()),
                        formatDate(fld_season_endDate.getText()))) {
                    loadSeasonList();
                    loadRoomSeasonCombobox();
                    Helper.showMessage("done");
                }
            }
        });
        // Add Pension For Hotel
        btn_addPensiontoHotel.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                detailManager.addPensiontoHotel(
                        hotel.getHotelID(),
                        ((Pension) cmb_pension.getSelectedItem()).getId(),
                        ((Pension) cmb_pension.getSelectedItem()).getPensionName()
                );
                loadPensionList();
                loadRoomPensionCombobox();
                Helper.showMessage("done");
            }
        });
        // Add Feature For Hotel
        btn_addHotelFeature.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                detailManager.addHotelFeature(
                        hotel.getHotelID(),
                        ((HotelFeature) cmb_hotelFeatures.getSelectedItem()).getId(),
                        ((HotelFeature) cmb_hotelFeatures.getSelectedItem()).getFeatureName()
                );
                loadHotelFeatureList();
                Helper.showMessage("done");
            }

        });

        // Add Room
        btn_addHotelRoom.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_type) || Helper.isFieldEmpty(fld_room_bedNumber) || !isNumeric(fld_room_bedNumber.getText()) || Helper.isFieldEmpty(fld_room_stock) || !isNumeric(fld_room_stock.getText()) || Helper.isFieldEmpty(fld_room_price_child) || !isNumeric(fld_room_price_child.getText()) || Helper.isFieldEmpty(fld_room_price_adult) || !isNumeric(fld_room_price_adult.getText())) {
                Helper.showMessage("fill");
            } else {
                if (detailManager.addHotelRoom(
                        hotel.getHotelID(),
                        ((Season) cmb_room_season.getSelectedItem()).getId(),
                        ((Pension) cmb_room_pension.getSelectedItem()).getId(),
                        fld_room_type.getText(),
                        Integer.parseInt(fld_room_bedNumber.getText()),
                        Integer.parseInt(fld_room_stock.getText()),
                        Integer.parseInt(fld_room_price_child.getText()),
                        Integer.parseInt(fld_room_price_adult.getText())
                )) {
                    loadHotelRoomList();
                    Helper.showMessage("done");
                }
            }
        });
    }

    private String formatDate(String input) {
        String[] arr = input.split("/");
        return arr[2] + "-" + arr[1] + "-" + arr[0];
    }

    public Boolean isValidDates(String dateToCheck1, String dateToCheck2) {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateToCheck1, DATE_TIME_FORMATTER);
            date = LocalDate.parse(dateToCheck2, DATE_TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            Helper.showMessage("wrongDateFormat");
        }
        return false;
    }

    private void loadSeasonList() {
        DefaultTableModel db = (DefaultTableModel) tbl_season.getModel();
        db.setRowCount(0);
        for (Season season : detailManager.getSeasonListByHotelID(hotel.getHotelID())) {
            row_season[0] = season.getId();
            row_season[1] = season.getSeasonName();
            row_season[2] = season.getStartDate();
            row_season[3] = season.getEndDate();
            mdl_season.addRow(row_season);
        }
    }

    private void loadPensionList() {
        DefaultTableModel db = (DefaultTableModel) tbl_pension.getModel();
        db.setRowCount(0);
        for (Pension pension : detailManager.getPensionList(hotel.getHotelID())) {
            row_pension[0] = pension.getId();
            row_pension[1] = pension.getPensionName();
            mdl_pension.addRow(row_pension);
        }
    }

    private void loadHotelFeatureList() {
        DefaultTableModel db = (DefaultTableModel) tbl_hotel_feature.getModel();
        db.setRowCount(0);
        for (HotelFeature hotelFeature : detailManager.getHotelFeatureListByID(hotel.getHotelID())) {
            row_hotelFeature[0] = hotelFeature.getFeatureName();
            mdl_hotelFeature.addRow(row_hotelFeature);
        }
    }

    private void loadHotelRoomList() {
        DefaultTableModel db = (DefaultTableModel) tbl_room.getModel();
        db.setRowCount(0);
        for (Room room : detailManager.getHotelRoomListByID(hotel.getHotelID())) {
            row_room[0] = room.getId();
            row_room[1] = detailManager.getSeasonNameByID(room.getSeasonID());
            row_room[2] = detailManager.getPensionNameByID(room.getPensionID());
            row_room[3] = room.getRoomType();
            row_room[4] = room.getBedNumber();
            row_room[5] = room.getStock();
            row_room[6] = room.getPriceChild();
            row_room[7] = room.getPriceAdult();
            mdl_room.addRow(row_room);
        }
    }

    private void loadPensionCombobox() {
        cmb_pension.removeAllItems();
        for (Pension pension : detailManager.getPensionTypeList()) {
            cmb_pension.addItem(pension);
        }
    }

    private void loadHotelFeaturesCombobox() {
        cmb_hotelFeatures.removeAllItems();
        for (HotelFeature hotelFeature : detailManager.getHotelFeaturesList()) {
            cmb_hotelFeatures.addItem(hotelFeature);
        }
    }

    private void loadRoomSeasonCombobox() {
        cmb_room_season.removeAllItems();
        for (Season season : detailManager.getSeasonListByHotelID(hotel.getHotelID())) {
            cmb_room_season.addItem(season);
        }
    }

    private void loadRoomPensionCombobox() {
        cmb_room_pension.removeAllItems();
        for (Pension pension : detailManager.getPensionList(hotel.getHotelID())) {
            cmb_room_pension.addItem(pension);
        }
    }

    public static boolean isNumeric(String maybeNumeric) {
        // We can use regex because we can check range of values
        return maybeNumeric != null && maybeNumeric.matches("[0-9]+");
    }
}
