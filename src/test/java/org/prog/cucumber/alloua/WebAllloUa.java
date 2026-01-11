package org.prog.cucumber.alloua;

import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.dto.PhoneDto;
import org.openqa.selenium.WebElement;
import org.prog.util.Container;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebAllloUa{

    public void requestNewPhone (){
        PhoneDto phoneDto = new PhoneDto();
    }

    public static WebDriver driver;
    @Given("I opened the search results for the query “телефон” on the Allo.ua website")
    public void getPhonesInfo() {
        driver.get("https://allo.ua/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15L));

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

        Container.DATA_HOLDER.put("phones_list", phones);


    }
}
