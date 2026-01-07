package org.prog.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    protected static Connection connection;

    @BeforeSuite
    public void setUp() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");
    }

    @AfterSuite
    public void tearDown() throws SQLException {
        connection.close();
    }
}
