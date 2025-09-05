package steps;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;

public class CheckOutStep {

    private static final Logger logger = LoggerFactory.getLogger(CheckOutStep.class);

    WebDriver driver;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    public CheckOutStep(WebDriver driver) {
        this.driver = driver;
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public void isOpened() {
        logger.info("Проверка открытия страницы оплаты");
        productsPage.open();
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.open()
                .checkout();
        assertEquals(checkoutPage.getTitle(),
                "Checkout: Your Information",
                "Открытие чекаута не выполнено");
    }

    public void testPositiveCheckout() {
        logger.info("Проверка оплаты с позитивными данными");
        productsPage.addToCart("Sauce Labs Backpack");
        cartPage.open()
                .checkout();
        checkoutPage.open()
                .enterInfo("First", "Last", "123456");
        assertEquals(checkoutPage.getTitle(),
                "Checkout: Overview",
                "Чекаут не выполнен");
    }



}
