package org.prog.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.prog.cucumber.alloua.SqlAlloUa;
import org.prog.cucumber.alloua.WebAlloUa;
import org.prog.pages.AlloUaPages;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.prog.util.WebDriverFactory;

import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.SQLException;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.prog.cucumber.alloua",
        tags = "@allo",
        plugin = {"pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
    private WebDriver driver;

    @BeforeSuite
    public void setUp() throws ClassNotFoundException, SQLException, MalformedURLException {
        String envType = System.getProperty("envType", "jenkins");
        this.driver = WebDriverFactory.getDriver(envType);
        WebAlloUa.alloUaPages = new AlloUaPages(driver);
        Class.forName("com.mysql.cj.jdbc.Driver");
        if ("jenkins".equals(envType)) {
            SqlAlloUa.connection =
                    DriverManager.getConnection("jdbc:mysql://mysql-db-1:3306/db", "user", "password");
        } else {
            SqlAlloUa.connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "user", "password");
        }
    }

    @AfterSuite
    public void tearDown() throws SQLException {
        driver.quit();
    }
}