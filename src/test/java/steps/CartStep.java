package steps;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class CartStep {

    private static final Logger logger = LoggerFactory.getLogger(CartStep.class);

    WebDriver driver;
    ProductsPage productsPage;
    CartPage cartPage;

    public CartStep(WebDriver driver) {
        this.driver = driver;
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    public void cartIsOpened() {
        logger.info("Проверка открытия страницы корзины");
        productsPage.addToCart("Sauce Labs Backpack")
                        .addToCart("Sauce Labs Bolt T-Shirt");
        cartPage.open();
        assertEquals(cartPage.getTitle(),
                "Your Cart",
                "Открытие корзины не выполнено");
    }

    public void checkRemoveButton() {
        logger.info("Проверка кнопки удаления товара");
        cartPage.remove("sauce-labs-bolt-t-shirt");
        assertFalse(cartPage.isItemDisplayed("sauce-labs-bolt-t-shirt"));
    }
}
