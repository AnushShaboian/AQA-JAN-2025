package org.prog.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.prog.cucumber.alloua.SqlAlloUa;
import org.prog.cucumber.alloua.WebAllloUa;
import org.prog.cucumber.steps.SqlSteps;
import org.prog.cucumber.steps.WebSteps;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
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

    @BeforeSuite
    public void setUp() throws ClassNotFoundException, SQLException, MalformedURLException {
        WebAllloUa.driver = getRemoteDriver();
        WebAllloUa.driver.manage().window().maximize();
        Class.forName("com.mysql.cj.jdbc.Driver");
        SqlAlloUa.connection =
                DriverManager.getConnection("jdbc:mysql://mysql-db-1:3306/db", "user", "password");
    }

    @AfterSuite
    public void tearDown() throws SQLException {
        SqlAlloUa.connection.close();
        if (WebAllloUa.driver != null) {
            WebAllloUa.driver.quit();
        }
    }

    public WebDriver getRemoteDriver() throws MalformedURLException {
        return new RemoteWebDriver(
                new URL("http://selenoid-selenoid-1:4444/wd/hub"), new ChromeOptions());
    }
}