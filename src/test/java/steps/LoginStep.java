package steps;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;

public class LoginStep {

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    public void auth(String username, String password) {
        logger.info("Открытие страницы и авторизация");
        loginPage.open()
                .login(username, password);
    }

    public void testWithPositiveCred() {
        logger.info("Тест с позитивными кредами");
        assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
    }

    public void testWithEmptyPassword() {
        logger.info("Негативный тест с пустым паролем");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не соответствует");
    }

    public void testWithEmptyUsername() {
        logger.info("Негативный тест с пустым логином");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не соответствует");
    }
    public void testWithTestCred() {
        logger.info("Проверка с тестовыми данными");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не соответствует");
    }
}
