package org.prog.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WebDriverFactory {

    public static WebDriver getDriver(String envType) throws MalformedURLException {
        switch (envType) {
            case "local":
                return new ChromeDriver();
            case "local-selenoid":
                return new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"), remoteChrome());
            default:
                return new RemoteWebDriver(
                        new URL("http://selenoid-selenoid-1:4444/wd/hub"), remoteChrome());
        }
    }

    private static ChromeOptions remoteChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            put("enableVideo", true);
            put("enableVNC", true);
        }});
        return chromeOptions;
    }
}
