package com.seaboyz;

import java.sql.Connection;
import java.sql.SQLException;

import com.seaboyz.database.PostgreSQLDatabase;

public class App {
    public static void main(String[] args) {
        try {
            Connection db = PostgreSQLDatabase.getConnection();
            db.close();
            System.out.println("Disconnected from database");
        } catch (SQLException e) {
            System.out.println("Fail connecting to database");
            e.printStackTrace();
        }
    }
}
