package com.saucedemo.cucumber.pages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {
    private final SelenideElement pageTitle = $("[data-test='title']");
    private final SelenideElement backpackAddButton = $("#add-to-cart-sauce-labs-backpack");
    private final SelenideElement cartBadge = $("[data-test='shopping-cart-badge']");
    private final SelenideElement cartLink = $("[data-test='shopping-cart-link']");

    @Then("открыта страница товаров")
    public void shouldBeOpened() {
        pageTitle.as("Заголовок страницы товаров")
                .shouldBe(visible)
                .shouldHave(exactText("Products"));
    }

    @When("пользователь добавляет рюкзак Sauce Labs в корзину")
    public void addBackpackToCart() {
        backpackAddButton.as("Кнопка добавления рюкзака Sauce Labs в корзину")
                .shouldBe(visible)
                .click();
    }

    @Then("счётчик корзины равен {int}")
    public void shouldHaveCartItemsCount(int expectedCount) {
        cartBadge.as("Счётчик товаров в корзине")
                .shouldBe(visible)
                .shouldHave(exactText(String.valueOf(expectedCount)));
    }

    @When("пользователь открывает корзину")
    public void openCart() {
        cartLink.as("Ссылка на корзину")
                .shouldBe(visible)
                .click();
    }
}
