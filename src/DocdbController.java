package com.mycompany.hospital;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DocdbController implements Initializable {

    @FXML
    private TableColumn<ModelTable, String> docAvColumn;
    @FXML
    private TableColumn<ModelTable, String> docIdColumn;
    @FXML
    private TableColumn<ModelTable, String> docNameColumn;
    @FXML
    private TableColumn<ModelTable, String> docPhoneNumberColumn;
    @FXML
    private Button homebtn;
    @FXML
    private Button refreshbtn;
    @FXML
    private Button addDocBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button updateBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField avText;
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TableView docTable;
    @FXML
    private TextField searchdocIdText;
    @FXML
    private TextField updateavText;
    @FXML

    private TextArea resultArea;
    private Connection connect;
    private PreparedStatement statement;

    String SQL = "SELECT * from doctor";
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();

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
                ob.add(new ModelTable(rs.getString("id"), rs.getString("name"),
                        rs.getString("phone"), rs.getString("available")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        docIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        docNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        docPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        docAvColumn.setCellValueFactory(new PropertyValueFactory<>("av"));
        docTable.getItems().addAll(ob);
    }

    @FXML
    void refresh(ActionEvent event) throws IOException {
        App.setRoot("docdb");
    }

    @FXML
    void home(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void delete(ActionEvent event) {
        connect = connectDB();
        try {
            /* + idText.toString() + "' AND name = '" + nameText.toString() + "' AND phone = '" + phoneText.toString() + "' AND availability = '" + avText.toString() "'";*/
            String sql = "DELETE FROM doctor WHERE id=?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, idText.getText());
            statement.executeUpdate();
            //fetRowList();
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
    void insertDoctor(ActionEvent event) {
        connect = connectDB();
        try {
            String sql = "INSERT INTO `doctor` (`id`,`name`,`phone`,`available`) VALUES (?,?,?,?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, idText.getText());
            statement.setString(2, nameText.getText());
            statement.setString(3, phoneText.getText());
            statement.setString(4, avText.getText());
            statement.execute();
            //fetRowList();
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
    void searchDoc(ActionEvent event) {
    }

    @FXML
    void update(ActionEvent event) {
        connect = connectDB();
        try {
            String sql = "UPDATE doctor SET available=? WHERE id=?";
            statement = connect.prepareStatement(sql);
            statement.setString(1, avText.getText());
            statement.setString(2, idText.getText());
            statement.executeUpdate();
            //fetRowList();
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
