package org.prog.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    protected static Connection connection;
    protected WebDriver driver;

    @BeforeSuite
    public void setUp() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");
    }

    @BeforeClass
    public void setUpBrowser() {
        // Браузер відкривається 1 раз перед початком тестів у поточному класі
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDownBrowser() {
        // Браузер закривається після того, як всі методи @Test у класі відпрацювали
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDown() throws SQLException {
        connection.close();
    }
}
