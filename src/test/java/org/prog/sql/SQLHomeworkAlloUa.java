package org.prog.sql;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.base.DBConnection;
import org.prog.dto.PhoneDto;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SQLHomeworkAlloUa extends DBConnection {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
    };

    @AfterMethod
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }

    public List<PhoneDto> getPhonesInfo() {
        driver.get("https://allo.ua/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement search = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("search"))
        );
        search.sendKeys("Телефон", Keys.ENTER);

        WebElement phoneName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[contains(@class,'product-card')]//a[contains(@class,'product-card__title')])[1]")
                )
        );

        WebElement phonePrice = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//div[contains(@class,'product-card')]//span[contains(@class,'sum')])[1]")
                )
        );

        String nameText = phoneName.getText();
        String priceText = phonePrice.getText().replaceAll("[^0-9]", "");
        int finalPrice = Integer.parseInt(priceText);
        Assert.assertFalse(phoneName.getText().isBlank(), "Phone name is empty");
        Assert.assertFalse(phonePrice.getText().isBlank(), "Phone price is empty");

        PhoneDto phoneDto = new PhoneDto(nameText, finalPrice);
        List<PhoneDto> phoneDtoList = new ArrayList<>();
        phoneDtoList.add(phoneDto);
        return phoneDtoList;
    }

    @Test
    public void writePhoneToDB() throws SQLException {
        List<PhoneDto> phoneDtos = getPhonesInfo();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO Phones (PhoneName, PhonePrice) VALUES " + "(?, ?)");
        for (PhoneDto phoneDto : phoneDtos) {
            preparedStatement.setString(1, phoneDto.getName());
            preparedStatement.setInt(2, phoneDto.getPrice());
            try {
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
