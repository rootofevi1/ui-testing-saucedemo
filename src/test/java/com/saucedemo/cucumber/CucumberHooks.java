package com.saucedemo.cucumber;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class CucumberHooks {
    @Before
    public void configureWebDriver() {
        Configuration.baseUrl = System.getProperty("testUrl");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.headless = Boolean.parseBoolean(
                System.getProperty("headless")
        );
        Configuration.timeout = Long.parseLong(
                System.getProperty("timeout")
        );
    }

    @After
    public void closeBrowser() {
        closeWebDriver();
    }
}
