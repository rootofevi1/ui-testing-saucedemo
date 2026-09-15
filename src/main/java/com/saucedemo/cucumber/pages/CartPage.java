package com.saucedemo.cucumber.pages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement pageTitle = $("[data-test='title']");
    private final SelenideElement productName = $("[data-test='inventory-item-name']");
    private final SelenideElement checkoutButton = $("#checkout");

    @Then("открыта страница корзины")
    public void shouldBeOpened() {
        pageTitle.as("Заголовок страницы корзины")
                .shouldBe(visible)
                .shouldHave(exactText("Your Cart"));
    }

    @Then("в корзине находится товар {string}")
    public void shouldContainProduct(String expectedProductName) {
        productName.as("Название товара в корзине")
                .shouldBe(visible)
                .shouldHave(exactText(expectedProductName));
    }

    @When("пользователь переходит к оформлению заказа")
    public void proceedToCheckout() {
        checkoutButton.as("Кнопка перехода к оформлению заказа")
                .shouldBe(visible)
                .click();
    }
}
