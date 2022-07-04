package com.mycompany.hospital;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class SecondaryController {

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
    private Button back;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField pf;

    @FXML
    private TextField tf;

    @FXML
    void switchToLogin() throws IOException {
        connect = connectDB();
        try {
            String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, tf.getText());
            statement.setString(2, pf.getText());
            result = statement.executeQuery();

            if (result.next()) {
                //if entered data is correct,, this area would be executed after.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("INFORMATION ALERT !");
                String s = "Logged In Successfully.";
                alert.setContentText(s);
                alert.show();
                App.setRoot("dashboard");

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error Message !");
                alert.setHeaderText("ERROR");
                String s = "Invalid Username or Password ! Try Again.";
                alert.setContentText(s);
                alert.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToPrimary(ActionEvent event) throws IOException {

        //Back Button
        App.setRoot("primary");
    }

}
