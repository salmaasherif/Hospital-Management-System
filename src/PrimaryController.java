package com.mycompany.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.time.Clock.system;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    
    
    
    @FXML
    private Button adminButton;
    @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("secondary");
    }

        @FXML
    private Button regAdminButton;

    @FXML
    void onRegAdminClick(ActionEvent event) throws IOException {

        App.setRoot("newAdmin");
        
    }

        @FXML
    void switchToPatient() throws IOException {
        App.setRoot("Patient");
    }
    
            @FXML
    void onExitButtonClick() throws IOException {
        System.exit(0);
    }

}
