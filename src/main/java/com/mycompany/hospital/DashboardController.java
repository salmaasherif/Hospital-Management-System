package com.mycompany.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DashboardController {

    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    public Connection connectDB() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "salma25may2000&");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @FXML
    private Button logoutButton1;

    @FXML
    private Button newPatientButton1;

    @FXML
    private Button setAppButton;

    @FXML
    private Button docDbButton;

    @FXML
    void onAddNewPatientClick(ActionEvent event) throws IOException {
        App.setRoot("newpatient");

    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) throws IOException {
        App.setRoot("primary");

    }

    @FXML
    void onDocDbButtonClick(ActionEvent event) throws IOException {
        App.setRoot("docdb");

    }

    @FXML
    void onSetAppClick(ActionEvent event) throws IOException {

        App.setRoot("setapp");
    }

}
