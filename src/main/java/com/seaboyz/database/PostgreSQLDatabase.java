package com.seaboyz.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgreSQLDatabase {
  private static Connection conn;

  private PostgreSQLDatabase() {
  };

  public static Connection getConnection() throws SQLException {
    if (conn == null) {
      connect();
    }
    return conn;
  }

  private static void connect() throws SQLException {
    // get username password postgreSql string from application.properties file
    Properties props = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream input = loader.getResourceAsStream("application.properties");

    try {
      props.load(input);
    } catch (IOException e) {
      System.out.println("Fail loading props from application.properites file");
      e.printStackTrace();
      return;
    }

    // Build connection string
    String url = "jdbc:postgresql://"
        + props.getProperty("hostname")
        + "/"
        + props.getProperty("dbname");
    String username = props.getProperty("username");
    String password = props.getProperty("password");

    // establish connection
    conn = DriverManager.getConnection(url, username, password);
    System.out.println("Connected to PostgreSQL server.");
  }
}
