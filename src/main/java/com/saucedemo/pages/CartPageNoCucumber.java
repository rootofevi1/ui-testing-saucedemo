package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPageNoCucumber {
    private final SelenideElement pageTitle = $("[data-test='title']");
    private final SelenideElement productName = $("[data-test='inventory-item-name']");
    private final SelenideElement checkoutButton = $("#checkout");

    public CartPageNoCucumber shouldBeOpened() {
        pageTitle.as("Заголовок страницы корзины").shouldBe(visible).shouldHave(exactText("Your Cart"));
        return this;
    }

    public CartPageNoCucumber shouldContainProduct(String expectedProductName) {
        productName.as("Название товара в корзине")
                .shouldBe(visible)
                .shouldHave(exactText(expectedProductName));
        return this;
    }

    public CheckoutPageNoCucumber proceedToCheckout() {
        checkoutButton.as("Кнопка перехода к оформлению заказа").shouldBe(visible).click();
        return new CheckoutPageNoCucumber();
    }
}
