package tests;

import plugins.retry.RetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Позитивный тест логина", description = "Проверка входа в аккаунт с валидными данными",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkPositiveLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(),
                "Products",
                "Логин не выполнен");
    }

    @Test(testName = "Негативный тест логина", description = "Проверка входа пользователя с пустым паролем",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "Сообщение об ошибке не соответствует");
    }

    @Test(testName = "Негативный тест логина", description = "Проверка входа пользователя с именем пользователя",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkLoginWithEmptyUsername() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не соответствует");
    }

    @Test(testName = "Негативный тест логина", description = "Проверка входа пользователя с тестовыми данными",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkLoginWithTestCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не соответствует");
    }

    @DataProvider(name = "Проверка логина с негативными данными")
    public Object[][] loginData() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Проверка логина с негативными данными")
    public void paramNegativeTest(String user, String password, String expectedErrorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(),
                expectedErrorMessage,
                "Сообщение об ошибке не соответствует");
    }
}
