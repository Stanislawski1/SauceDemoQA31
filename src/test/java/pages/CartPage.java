package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    @FindBy(id = "checkout")
    private WebElement CHECKOUT;
    @FindBy(id = "continue-shopping")
    private WebElement CONTINUE_SHOPPING_BUTTON;
    @FindBy(className ="title" )
    private WebElement TITLE;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        logger.info("Открытие страницы корзины");
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    public CheckoutPage checkout() {
        logger.info("Нажатие на кнопку оплаты");
        CHECKOUT.click();
        return new CheckoutPage(driver);
    }

    public CartPage remove(String itemId) {
        logger.info("Удаление товара");
         driver.findElement(By.id("remove-" + itemId));
        return this;
    }

    public ProductsPage continueShopping() {
        logger.info("Нажатие на кнопку 'Продолжить покупку'");
        CONTINUE_SHOPPING_BUTTON.click();
        return new ProductsPage(driver);
    }

    public CartPage getTitle() {
        TITLE.click();
        return this;
    }

    public boolean isItemDisplayed(String itemId) {
        logger.info("Проверка изображения товара");
        try {
            return driver.findElement(By.id(itemId)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
