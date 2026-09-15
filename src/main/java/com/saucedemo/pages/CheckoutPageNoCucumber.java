package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPageNoCucumber {
    private final SelenideElement pageTitle = $("[data-test='title']");
    private final SelenideElement firstNameInput = $("#first-name");
    private final SelenideElement lastNameInput = $("#last-name");
    private final SelenideElement postalCodeInput = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement finishButton = $("#finish");
    private final SelenideElement errorMessage = $("[data-test='error']");
    private final SelenideElement overviewProductName = $("[data-test='inventory-item-name']");
    private final SelenideElement completeHeader = $("[data-test='complete-header']");

    public CheckoutPageNoCucumber shouldShowCustomerInformationStep() {
        pageTitle.as("Заголовок шага ввода данных покупателя")
                .shouldBe(visible)
                .shouldHave(exactText("Checkout: Your Information"));
        return this;
    }

    public CheckoutPageNoCucumber fillCustomerInformation(String firstName, String lastName, String postalCode) {
        firstNameInput.as("Поле имени покупателя").shouldBe(visible).setValue(firstName);
        lastNameInput.as("Поле фамилии покупателя").shouldBe(visible).setValue(lastName);
        postalCodeInput.as("Поле почтового индекса").shouldBe(visible).setValue(postalCode);
        return this;
    }

    public CheckoutPageNoCucumber continueCheckout() {
        continueButton.as("Кнопка продолжения оформления заказа").shouldBe(visible).click();
        return this;
    }

    public CheckoutPageNoCucumber shouldShowOverviewStep() {
        pageTitle.as("Заголовок страницы обзора заказа")
                .shouldBe(visible)
                .shouldHave(exactText("Checkout: Overview"));
        return this;
    }

    public CheckoutPageNoCucumber shouldContainProduct(String expectedProductName) {
        overviewProductName.as("Название товара на странице обзора заказа")
                .shouldBe(visible)
                .shouldHave(exactText(expectedProductName));
        return this;
    }

    public CheckoutPageNoCucumber finishCheckout() {
        finishButton.as("Кнопка завершения оформления заказа").shouldBe(visible).click();
        return this;
    }

    public CheckoutPageNoCucumber shouldShowCompletedOrder() {
        pageTitle.as("Заголовок страницы завершённого заказа")
                .shouldBe(visible)
                .shouldHave(exactText("Checkout: Complete!"));
        completeHeader.as("Подтверждение успешного оформления заказа")
                .shouldBe(visible)
                .shouldHave(exactText("Thank you for your order!"));
        return this;
    }

    public CheckoutPageNoCucumber shouldShowRequiredFieldError(String expectedMessage) {
        errorMessage.as("Сообщение о незаполненном обязательном поле")
                .shouldBe(visible)
                .shouldHave(exactText(expectedMessage));
        return this;
    }
}
