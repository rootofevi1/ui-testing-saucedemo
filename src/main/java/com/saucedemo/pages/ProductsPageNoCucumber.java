package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductsPageNoCucumber {
    public static final String BACKPACK_NAME = "Sauce Labs Backpack";

    private final SelenideElement pageTitle = $("[data-test='title']");
    private final SelenideElement backpackAddButton = $("#add-to-cart-sauce-labs-backpack");
    private final SelenideElement cartBadge = $("[data-test='shopping-cart-badge']");
    private final SelenideElement cartLink = $("[data-test='shopping-cart-link']");

    public ProductsPageNoCucumber shouldBeOpened() {
        pageTitle.as("Заголовок страницы товаров").shouldBe(visible).shouldHave(exactText("Products"));
        return this;
    }

    public ProductsPageNoCucumber addBackpackToCart() {
        backpackAddButton.as("Кнопка добавления рюкзака Sauce Labs в корзину").shouldBe(visible).click();
        return this;
    }

    public ProductsPageNoCucumber shouldHaveCartItemsCount(int expectedCount) {
        cartBadge.as("Счётчик товаров в корзине")
                .shouldBe(visible)
                .shouldHave(exactText(String.valueOf(expectedCount)));
        return this;
    }

    public CartPageNoCucumber openCart() {
        cartLink.as("Ссылка на корзину").shouldBe(visible).click();
        return new CartPageNoCucumber();
    }
}
