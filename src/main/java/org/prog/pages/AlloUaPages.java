package org.prog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.dto.PhoneDto;
import org.prog.util.Container;

import java.sql.Connection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AlloUaPages {
    private final static String URL = "https://allo.ua/";
    private final WebDriver driver;
    private final WebDriverWait wait;
    public static Connection connection;

    private final By SEARCH_INPUT = By.name("search");
    private final By PRODUCT_CARDS = By.xpath("//div[contains(@class,'products-layout__item')]");
    private final By PRODUCT_NAME = By.xpath(".//a[contains(@class,'product-card__title')]");
    private final By PRODUCT_PRICE = By.xpath(".//div[contains(@class, 'v-pb')]//span[contains(@class,'sum')]");

    public AlloUaPages (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void loadSite(){
        driver.get(URL);
    }

    public void openPhonesPage(){
        WebElement search = wait.until(
                ExpectedConditions.elementToBeClickable(SEARCH_INPUT));
        search.sendKeys("Телефон", Keys.ENTER);
    }

    public void getPhonesList(){
        List<WebElement> productCards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_CARDS));
        List<PhoneDto> phones = new ArrayList<>();
        int phonesCount = Math.min(productCards.size(), 5);

        for (int i = 0; i < phonesCount; i++) {
            WebElement card = driver.findElements(PRODUCT_CARDS).get(i);

            String name = card.findElement(PRODUCT_NAME).getText();
            String priceText = card.findElement(PRODUCT_PRICE).getText().replaceAll("[\\s\\u00A0]", "");
            int price = Integer.parseInt(priceText);
            phones.add(new PhoneDto(name, price));
        }
        Container.DATA_HOLDER.put("phones_list", phones);
    }
}
