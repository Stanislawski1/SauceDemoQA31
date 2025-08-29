package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import plugins.allure.AllureUtils;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductsPage extends BasePage {

    private final By TITLE = By.className("title");
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']" +
            "//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы продуктов")
    public void open() {
        driver.get(BASE_URL + "inventory.html");
        AllureUtils.takeScreenshot(driver);
    }


    @Step("Проверка открытия страницы продуктов")
    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Products']")));
        AllureUtils.takeScreenshot(driver);
    }

    @Step("Нажатие кнопки сортировки")
    public void sortingFilters(String filterOption) {
        By filterDropdownLocator = By.className("product_sort_container");
        Select filterDropdown = new Select(driver.findElement(filterDropdownLocator));
        filterDropdown.selectByVisibleText(filterOption);
        AllureUtils.takeScreenshot(driver);
    }

    @Step("Проверка первого товара после сортировки")
    public String getFirstProductTitle() {
        By productNameLocator = By.className("inventory_item_name");
        List<WebElement> productNames = driver.findElements(productNameLocator);
        if (productNames.isEmpty()) {
            throw new NoSuchElementException("No products found on the page");
        }
        AllureUtils.takeScreenshot(driver);
        return productNames.get(0).getText();

    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление товара с именем: '{product}' в корзину и нажатие на кнопку")
    public void addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        AllureUtils.takeScreenshot(driver);
    }
}
