package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckoutPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutPage.class);

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement FIRST_NAME;
    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement LAST_NAME;
    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement POSTAL_CODE;
    @FindBy(xpath = "//*[@id='continue']")
    private WebElement CONTINUE_BUTTON;
    @FindBy(className = "title")
    private WebElement TITLE;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage open() {
        logger.info("Открытие страницы оплаты");
        driver.get(BASE_URL + "checkout-step-one.html");
        return this;
    }

    public ProductsPage enterInfo(String first_name, String last_name, String zip) {
        logger.info("Ввод данных для оплаты");
        logger.debug("Ввод first name: {}", first_name);
        wait.until(ExpectedConditions.visibilityOf(FIRST_NAME));
        FIRST_NAME.sendKeys(first_name);
        logger.debug("Ввод last name: {}", last_name);
        wait.until(ExpectedConditions.visibilityOf(LAST_NAME));
        LAST_NAME.sendKeys(last_name);
        logger.debug("Ввод zip: {}", zip);
        wait.until(ExpectedConditions.visibilityOf(POSTAL_CODE));
        POSTAL_CODE.sendKeys(zip);
        logger.info("Нажатие на кнопку продолжить");
        CONTINUE_BUTTON.click();
        return new ProductsPage(driver);
    }

    public String getTitle() {
        logger.info("Проверка перехода на следующию страницу");
        return TITLE.getText();
    }
}
