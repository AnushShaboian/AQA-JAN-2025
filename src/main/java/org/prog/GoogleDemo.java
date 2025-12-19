package org.prog;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class GoogleDemo {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://google.com/");
            Thread.sleep(500);
            // ACCEPT COOKIES IF PRESENT
            List<WebElement> cookieLink =
                    driver.findElements(By.xpath("//a[contains(@href,'technologies/cookies')]"));
            Thread.sleep(500);
            if (!cookieLink.isEmpty() && cookieLink.get(0).isDisplayed()) {
                List<WebElement> buttons = driver.findElements(By.tagName("button"));
                Thread.sleep(500);
                buttons.get(3).click();
            }
            Thread.sleep(500);
            //SEARCH FOR CELEBRITY
            WebElement searchInput = driver.findElement(By.name("q"));
            Thread.sleep(500);
            for (int i = 0; i < "Ben Affleck".toCharArray().length; i++) {
                searchInput.sendKeys(String.valueOf("Ben Affleck".toCharArray()[i]));
                Thread.sleep(250);
            }
            searchInput.sendKeys(Keys.ENTER);
            Thread.sleep(500);

            System.out.println("So far so good");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
