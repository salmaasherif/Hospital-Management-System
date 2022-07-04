package com.mycompany.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Salma
 */
public class ConnectDB {

    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    public Connection connectDB() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "root");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
