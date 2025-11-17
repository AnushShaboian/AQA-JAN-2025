package org.prog.testng;

import org.openqa.selenium.By;
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
import java.util.List;

public class W3SchoolsTest {

    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testW3Schools() {
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_images_trulli");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30L));
        WebElement acceptCookiesButton =
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accept-choices")));
        acceptCookiesButton.click();

        List<WebElement> imagesEmpty = driver.findElements(By.xpath("//img[@src='pic_trulli.jpg']"));
        Assert.assertTrue(imagesEmpty.isEmpty(),
                "Expected list of images to eb empty because image is in iframe!");

        WebElement iframe = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframe);
        List<WebElement> imagesPresent = driver.findElements(By.xpath("//img[@src='pic_trulli.jpg']"));
        Assert.assertFalse(imagesPresent.isEmpty(),
                "Expected list of images to be NOT empty because selenium is INSIDE iframe!");
    }
}
