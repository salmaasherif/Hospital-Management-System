package com.mycompany.hospital;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SetAppController implements Initializable {

    @FXML
    private Button addDocBtn;
    @FXML
    private TableColumn<Appointment, String> dcol;
    @FXML
    private Button deleteBtn;
    @FXML
    private TextField dname;
    @FXML
    private TableView<Appointment> apptable;
    @FXML
    private Button homebtn;
    @FXML
    private TableColumn<Appointment, String> pcol;
    @FXML
    private TextField pname;
    @FXML
    private Button refreshbtn;
    @FXML
    private TableColumn<Appointment, String> scol;
    @FXML
    private TextField section;
    @FXML
    private TableColumn<Appointment, String> tcol;
    @FXML
    private TextField time;
    @FXML
    private Button updateBtn;

    private TextArea resultArea;
    private Connection connect;
    private PreparedStatement statement;

    String SQL = "SELECT * from appointment";
    ObservableList<Appointment> ob = FXCollections.observableArrayList();

    public Connection connectDB() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "salma25may2000&");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connect = connectDB();
            ResultSet rs = connect.createStatement().executeQuery(SQL);
            while (rs.next()) {
                ob.add(new Appointment(rs.getString("pname"), rs.getString("dname"),
                        rs.getString("time"), rs.getString("section")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pcol.setCellValueFactory(new PropertyValueFactory<>("pname"));
        dcol.setCellValueFactory(new PropertyValueFactory<>("dname"));
        tcol.setCellValueFactory(new PropertyValueFactory<>("time"));
        scol.setCellValueFactory(new PropertyValueFactory<>("section"));
        apptable.setItems(ob);
    }

    @FXML
    void addapp(ActionEvent event) {
        connect = connectDB();
        try {
            String sql = "INSERT INTO `appointment` (`pname`,`dname`,`time`,`section`) VALUES (?,?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, pname.getText());
            statement.setString(2, dname.getText());
            statement.setString(3, time.getText());
            statement.setString(4, section.getText());
            statement.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("INFORMATION ALERT !");
            String s = "Added Successfully.";
            alert.setContentText(s);
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message !");
            alert.setHeaderText("ERROR !");
            String s = "Failed. Try Again !";
            alert.setContentText(s);
            alert.show();
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        connect = connectDB();
        try {
            String sql = "DELETE FROM appointment WHERE pname=?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, pname.getText());
            statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("INFORMATION ALERT !");
            String s = "Deleted Successfully.";
            alert.setContentText(s);
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message !");
            alert.setHeaderText("ERROR !");
            String s = "Failed. Try Again !";
            alert.setContentText(s);
            alert.show();
            e.printStackTrace();
        }
    }

    @FXML
    void home(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void refresh(ActionEvent event) throws IOException {
        App.setRoot("setapp");
    }

    @FXML
    void update(ActionEvent event) {
        connect = connectDB();
        try {
            String sql = "UPDATE appointment SET time=? WHERE pname=?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, time.getText());
            statement.setString(2, pname.getText());
            statement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("INFORMATION ALERT !");
            String s = "Updated Successfully.";
            alert.setContentText(s);
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Message !");
            alert.setHeaderText("ERROR !");
            String s = "Failed. Try Again !";
            alert.setContentText(s);
            alert.show();
            e.printStackTrace();
        }
    }

}
