package com.saucedemo.pages.aiassistedsolution;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.saucedemo.pages.ProductsPageNoCucumber;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * AI-assisted-solution.
 *
 * Исходный код сгенерирован GigaChat.
 * Для интеграции в проект были добавлены package/import
 * и заменён отсутствующий InventoryPage
 * на существующий ProductsPageNoCucumber.
 */

public class LoginPageAiAssistedSolution {

    private final String BASE_URL = "https://www.saucedemo.com/";

    private SelenideElement usernameField = $("#user-name");
    private SelenideElement passwordField = $("#password");
    private SelenideElement loginButton = $(".submit-button");
    private SelenideElement errorMessage =
            $(byAttribute("data-test", "error"));

    public void openPage() {
        open(BASE_URL);
    }

    public ProductsPageNoCucumber loginSuccessfully(
            String username,
            String password
    ) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();

        return new ProductsPageNoCucumber();
    }

    public void shouldSeeErrorMessage(String expectedText) {
        errorMessage
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }

    public String getErrorMessageText() {
        return errorMessage
                .shouldBe(Condition.visible)
                .getText();
    }
}
