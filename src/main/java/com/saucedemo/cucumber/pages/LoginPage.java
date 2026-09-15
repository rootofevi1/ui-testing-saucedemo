package com.saucedemo.cucumber.pages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private static final String LOGIN_PAGE_PATH = "/";
    private static final String STANDARD_USER = "standard_user";
    private static final String STANDARD_PASSWORD = "secret_sauce";

    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("[data-test='error']");

    @Given("пользователь находится на странице авторизации")
    public void openPage() {
        open(LOGIN_PAGE_PATH);
        usernameInput.as("Поле имени пользователя на странице авторизации")
                .shouldBe(visible);
    }

    @When("пользователь авторизуется с валидными данными")
    public void loginWithValidCredentials() {
        enterCredentialsAndSubmit(STANDARD_USER, STANDARD_PASSWORD);
    }

    @When("пользователь авторизуется с логином {string} и паролем {string}")
    public void loginWithCredentials(String username, String password) {
        enterCredentialsAndSubmit(username, password);
    }

    @Then("отображается ошибка авторизации {string}")
    public void shouldShowError(String expectedMessage) {
        errorMessage.as("Сообщение об ошибке авторизации")
                .shouldBe(visible)
                .shouldHave(exactText(expectedMessage));
    }

    private void enterCredentialsAndSubmit(String username, String password) {
        usernameInput.as("Поле имени пользователя").shouldBe(visible).setValue(username);
        passwordInput.as("Поле пароля").shouldBe(visible).setValue(password);
        loginButton.as("Кнопка входа").shouldBe(visible).click();
    }
}
