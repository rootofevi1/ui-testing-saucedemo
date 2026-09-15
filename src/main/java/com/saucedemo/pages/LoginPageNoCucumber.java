package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Manual-solution
 */

public class LoginPageNoCucumber {
    private static final String LOGIN_PAGE_PATH = "/";

    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("[data-test='error']");

    public LoginPageNoCucumber openPage() {
        open(LOGIN_PAGE_PATH);
        usernameInput.as("Поле имени пользователя на странице авторизации").shouldBe(visible);
        return this;
    }

    public ProductsPageNoCucumber loginAs(String username, String password) {
        enterCredentialsAndSubmit(username, password);
        return new ProductsPageNoCucumber();
    }

    public LoginPageNoCucumber loginWithInvalidCredentials(String username, String password) {
        enterCredentialsAndSubmit(username, password);
        return this;
    }

    public LoginPageNoCucumber shouldShowError(String expectedMessage) {
        errorMessage.as("Сообщение об ошибке авторизации")
                .shouldBe(visible)
                .shouldHave(exactText(expectedMessage));
        return this;
    }

    private void enterCredentialsAndSubmit(String username, String password) {
        usernameInput.as("Поле имени пользователя").shouldBe(visible).setValue(username);
        passwordInput.as("Поле пароля").shouldBe(visible).setValue(password);
        loginButton.as("Кнопка входа").shouldBe(visible).click();
    }
}
