package org.prog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage {

    private WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadPage() {
        driver.get("https://google.com/");
    }

    public boolean isCookiesPresent() {
        return false;
    }

    public void acceptCookies() {

    }

    public void setSearchValue(String text) {
        driver.findElement(By.name("q")).sendKeys(text);
    }

    public void executeSearch() {

    }

    public void luckySearch() {

    }
}
