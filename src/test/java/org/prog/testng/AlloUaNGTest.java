package org.prog.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

//TODO: create separate test-ng.xml file with this class only
//TODO: move your selenium test to this class
//TODO: Add assertion that goods name is not null
//TODO: Add assertion that goods name has > 0 length

public class AlloUaNGTest {
    private WebDriver driver;

    @BeforeSuite
        private void setUp () {
        driver = new ChromeDriver();
    };

    @AfterSuite
        private void tearDown () {
        if (driver !=null){
            driver.quit();
        }
    }

        @Test
        public void testAlloUa () throws InterruptedException {
        driver.get("https://allo.ua/");

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30L));
        WebElement elementSearch = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
        elementSearch.click();

        elementSearch.sendKeys("Samsung Galaxy S23 Ultra 12/512Gb Green");
        elementSearch.sendKeys(Keys.ENTER);

        WebDriverWait webDriverWait2 = new WebDriverWait(driver, Duration.ofSeconds(60L));
        WebElement card = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("v-btn--cart")));

        Thread.sleep(5000);

        WebElement firstPhone = driver.findElement(By.xpath("//a[contains(@class,'product-card__title')]"));
            String text = firstPhone.getText();
            Assert.assertFalse(text.isEmpty(), "String is null");
            Assert.assertTrue(text.length() > 0, "String length = 0");
            System.out.println(text);

    }
}
