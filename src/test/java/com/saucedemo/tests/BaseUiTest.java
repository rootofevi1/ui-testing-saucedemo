package com.saucedemo.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseUiTest {
    protected static final String STANDARD_USER = "standard_user";
    protected static final String STANDARD_PASSWORD = "secret_sauce";

    @BeforeAll
    static void configureBrowser() {
        Configuration.baseUrl = System.getProperty("testUrl");
        Configuration.browser = System.getProperty("browser");
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless"));
        Configuration.timeout = Long.parseLong(System.getProperty("timeout"));
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }
}
