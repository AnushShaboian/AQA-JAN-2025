package org.prog.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.prog.cucumber.alloua.SqlAlloUa;
import org.prog.cucumber.alloua.WebAllloUa;
import org.prog.cucumber.steps.SqlSteps;
import org.prog.cucumber.steps.WebSteps;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.DriverManager;
import java.sql.SQLException;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.prog.cucumber.alloua",
        tags = "@allo"
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void setUp() throws ClassNotFoundException, SQLException {
        WebAllloUa.driver = new ChromeDriver();
        WebAllloUa.driver.manage().window().maximize();
        Class.forName("com.mysql.cj.jdbc.Driver");
        SqlAlloUa.connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");
    }

    @AfterSuite
    public void tearDown() throws SQLException {
        SqlAlloUa.connection.close();
        if (WebAllloUa.driver != null) {
            WebAllloUa.driver.quit();
        }
    }
}