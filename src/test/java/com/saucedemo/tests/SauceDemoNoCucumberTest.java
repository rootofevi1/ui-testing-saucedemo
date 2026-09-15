package com.saucedemo.tests;

import com.saucedemo.pages.CartPageNoCucumber;
import com.saucedemo.pages.LoginPageNoCucumber;
import com.saucedemo.pages.ProductsPageNoCucumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SauceDemoNoCucumberTest extends BaseUiTest {
    private static final String INVALID_LOGIN_ERROR =
            "Epic sadface: Username and password do not match any user in this service";
    private static final String REQUIRED_FIRST_NAME_ERROR = "Error: First Name is required";

    @Test
    @DisplayName("Пользователь успешно авторизуется, добавляет товар и оформляет заказ")
    void userCanCompletePurchase() {
        ProductsPageNoCucumber productsPage = new LoginPageNoCucumber()
                .openPage()
                .loginAs(STANDARD_USER, STANDARD_PASSWORD)
                .shouldBeOpened()
                .addBackpackToCart()
                .shouldHaveCartItemsCount(1);

        CartPageNoCucumber cartPage = productsPage.openCart()
                .shouldBeOpened()
                .shouldContainProduct(ProductsPageNoCucumber.BACKPACK_NAME);

        cartPage.proceedToCheckout()
                .shouldShowCustomerInformationStep()
                .fillCustomerInformation("Ivan", "Ivanov", "630000")
                .continueCheckout()
                .shouldShowOverviewStep()
                .shouldContainProduct(ProductsPageNoCucumber.BACKPACK_NAME)
                .finishCheckout()
                .shouldShowCompletedOrder();
    }

    @Test
    @DisplayName("Авторизация с неверными данными отображает понятную ошибку")
    void invalidCredentialsShowError() {
        new LoginPageNoCucumber()
                .openPage()
                .loginWithInvalidCredentials("invalid_user", "wrong_password")
                .shouldShowError(INVALID_LOGIN_ERROR);
    }

    @Test
    @DisplayName("Оформление заказа без обязательных данных отображает ошибку имени")
    void checkoutWithoutRequiredFieldsShowsError() {
        new LoginPageNoCucumber()
                .openPage()
                .loginAs(STANDARD_USER, STANDARD_PASSWORD)
                .shouldBeOpened()
                .addBackpackToCart()
                .openCart()
                .shouldBeOpened()
                .proceedToCheckout()
                .shouldShowCustomerInformationStep()
                .continueCheckout()
                .shouldShowRequiredFieldError(REQUIRED_FIRST_NAME_ERROR);
    }
}

/*
./gradlew allureReport --clean
./gradlew clean test allureReport
 */
