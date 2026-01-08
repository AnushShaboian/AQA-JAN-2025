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

    public List <PhoneDto> getPhonesInfo() {
        driver.get("https://allo.ua/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30L));

        WebElement search = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("search"))
        );
        search.sendKeys("Телефон", Keys.ENTER);

        List<WebElement> productCards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class,'products-layout__item')]")
                )
        );

        List<PhoneDto> phones = new ArrayList<>();

        int phonesCount = Math.min(productCards.size(), 5);

        for (int i = 0; i < phonesCount; i++) {
            WebElement card = productCards.get(i);

            WebElement nameElement = card.findElement(
                    By.xpath(".//a[contains(@class,'product-card__title')]")
            );

            WebElement priceElement = card.findElement(
                    By.xpath(".//span[contains(@class,'sum')]")
            );

            String name = nameElement.getText();
            String priceText = priceElement.getText().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(priceText);

            Assert.assertFalse(name.isBlank(), "Phone name is empty");
            Assert.assertTrue(price > 0, "Phone price is invalid");

            phones.add(new PhoneDto(name, price));
        }

        return phones;
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