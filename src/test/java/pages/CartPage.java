package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private final By CHECKOUT = By.id("checkout");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By TITLE = By.className("title");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "cart.html");
    }

    public void checkout() {
        driver.findElement(CHECKOUT).click();
    }

    public WebElement remove(String itemId) {
        return driver.findElement(By.id("remove-" + itemId));
    }

    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public boolean isItemDisplayed(String itemId) {
        try {
            return driver.findElement(By.id(itemId)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
