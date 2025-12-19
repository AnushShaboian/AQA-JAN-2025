package org.prog;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//TODO: go to allo.ua
//TODO: search for ANY PHONE
//TODO: print phone name for 1st phone in search
//TODO: For example: Apple iPhone 16 Pro Max 256GB Desert Titanium (MYWX3)
public class SeleniumHomeWork {

    public static void main(String[] args) throws InterruptedException{
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
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
            System.out.println(firstPhone.getText());

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

    }
}
