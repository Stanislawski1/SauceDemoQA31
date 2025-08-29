package tests;

import io.qameta.allure.*;
import plugins.retry.RetryAnalyzer;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {


    @Owner("Stanislaw")
    @Link("")
    @Description("Тест перехода на страницу оплаты")
    @Epic("SauceDemo checkout")
    @Feature("checkout")
    @Story("Test of checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Позитивный тест страницы оплаты", description = "Проверка перехода на страницу оплаты",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkСheckoutPageIsOpened() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.open();
        cartPage.checkout();
        assertEquals(checkoutPage.getTitle(),
                "Checkout: Your Information",
                "Открытие чекаута не выполнено");
    }


    @Owner("Stanislaw")
    @Link("")
    @Description("Позитивный тест оплаты")
    @Epic("SauceDemo checkout test")
    @Feature("checkout test")
    @Story("Test of checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Позитивный тест оплаты", description = "Проверка оплаты пользователем",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkPositiveСheckout() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.open();
        cartPage.checkout();
        checkoutPage.open();
        checkoutPage.enterInfo("First", "Last", "123456");
        assertEquals(checkoutPage.getTitle(),
                "Checkout: Overview",
                "Чекаут не выполнен");
    }
}
