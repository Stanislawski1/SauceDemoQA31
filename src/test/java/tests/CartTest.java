package tests;

import plugins.retry.RetryAnalyzer;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CartTest extends BaseTest {

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
