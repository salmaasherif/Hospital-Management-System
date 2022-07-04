package com.mycompany.hospital;

import com.mycompany.hospital.App;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewPatientController {
    
    
    public Connection connectDB(){
    try{
        connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital","root", "salma25may2000&");
        return connect;}
        catch(Exception e){ e.printStackTrace();}
        return null;
    }
        
    private Connection connect ;
    private PreparedStatement statement ;
    private ResultSet result;
 
        
    @FXML
    private TextField pid;

    @FXML
    private TextField pname;
    @FXML
    private Button regButton;
        @FXML
    private Button homeButton;

    @FXML
    void addNewPatient() throws IOException{
        connect = connectDB();
        try{
            String sql = "INSERT INTO `patient` (`pid`,`pname`) VALUES (?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, pid.getText());
            statement.setString(2, pname.getText());
            statement.execute();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("INFORMATION ALERT !");
            String s ="Registerd Successfully.";
            alert.setContentText(s);
            alert.show();
        }
        catch(Exception e){                
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error Message !");
            alert.setHeaderText("ERROR !");
            String s ="Failed. Try Again !";
            alert.setContentText(s);
            alert.show();
            e.printStackTrace();}
    }

    @FXML
    void switchToDashboard(ActionEvent event) throws IOException {

        App.setRoot("dashboard");
        
    }

}
