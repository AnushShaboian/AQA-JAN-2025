package org.prog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class W3SchoolsPage {

    private final static String URL =
            "https://www.w3schools.com/html/tryit.asp?filename=tryhtml_images_trulli";

    private final WebDriver driver;

    public W3SchoolsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadPage() {
        driver.get(URL);
    }

    public void acceptCookiesIfPresent() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5L));
        WebElement acceptCookiesButton =
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accept-choices")));
        acceptCookiesButton.click();
    }

    public boolean isElementPresentInCurrentContent(By selector) {
        // case if element is present >> isEmpty == false
        // case if element is missing >> isEmpty == true
        return !driver.findElements(selector).isEmpty();
    }

    public void switchToFrame(By selector) {
        driver.switchTo().frame(driver.findElement(selector));
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
