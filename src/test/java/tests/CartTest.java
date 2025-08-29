package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import plugins.retry.RetryAnalyzer;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CartTest extends BaseTest {


    @Owner("Stanislaw")
    @Link("")
    @Description("Тест проверки страницы корзины")
    @Epic("SauceDemo open page")
    @Feature("open page")
    @Story("Opening the page with positive cred")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Тест открытия страницы корзины", description = "Прверка открытия страницы корзины",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkCartIsOpened() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        cartPage.open();
        assertEquals(cartPage.getTitle(),
                "Your Cart",
                "Открытие корзины не выполнено");
    }


    @Owner("Stanislaw")
    @Link("")
    @Description("Тест кнопки удаления")
    @Epic("SauceDemo remove button")
    @Feature("Remove button")
    @Story("Test of remove button")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Тест кнопки удаления", description = "Проверка удаления выбранного товара",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkItemIsRemoved() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        cartPage.open();
        cartPage.remove("sauce-labs-bolt-t-shirt");
        assertFalse(cartPage.isItemDisplayed("sauce-labs-bolt-t-shirt"));
    }


    @Owner("Stanislaw")
    @Link("")
    @Description("Тест возвращения на страницу товаров")
    @Epic("SauceDemo back to product page")
    @Feature("Product page")
    @Story("Test of return to product page")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Тест возвращения на страницу товаров", description = "Проверка возвращения на страницу товаров",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkContinueShopping() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.open();
        cartPage.continueShopping();
        assertEquals(productsPage.getTitle(),
                "Products",
                "Возвращение на страницу товаров не выполнено");
    }
}
