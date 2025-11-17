package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.prog.pages.W3SchoolsPage;
import org.testng.Assert;

public class WebSteps {

    public static W3SchoolsPage w3SchoolsPage;

    @Given("I load W3Shcools Page")
    public void loadPage() {
        w3SchoolsPage.loadPage();
    }

    @When("I accept cookies if present")
    public void acceptCookies() {
        w3SchoolsPage.acceptCookiesIfPresent();
    }

    @Then("I can see content of iFrame")
    public void confirmIframe() {
        Assert.assertFalse(w3SchoolsPage.isElementPresentInCurrentContent(
                By.xpath("//img[@src='pic_trulli.jpg']")));

        w3SchoolsPage.switchToFrame(By.id("iframeResult"));

        Assert.assertTrue(w3SchoolsPage.isElementPresentInCurrentContent(
                By.xpath("//img[@src='pic_trulli.jpg']")));
    }
}
