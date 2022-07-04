package com.mycompany.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class NewAdminController {

        
        private Connection connect ;
    private PreparedStatement statement ;
    private ResultSet result;
    
    public Connection connectDB(){
    try{
        connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital","root", "salma25may2000&");
        return connect;}
        catch(Exception e){ e.printStackTrace();}
        return null;
    }   
        @FXML
    private TextField aid;
    
    @FXML
    private Button homeButton;

    @FXML
    private PasswordField password;

    @FXML
    private Button regButton;

    @FXML
    private TextField username;

    @FXML
    void addNewAdmin(ActionEvent event) {

                connect = connectDB();
        try{
            String sql = "INSERT INTO `admin` (`id`,`username`,`password`) VALUES (?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, aid.getText());
            statement.setString(2, username.getText());
            statement.setString(3, password.getText());
            statement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("INFORMATION ALERT !");
            String s ="Registerd Successfully.";
            alert.setContentText(s);
            alert.show();
        }
        catch(Exception e){                
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message !");
            alert.setHeaderText("ERROR !");
            String s ="Failed. Try Again !";
            alert.setContentText(s);
            alert.show();
            e.printStackTrace();}
        
    }
    
        @FXML
    void switchToPrimary(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

}
