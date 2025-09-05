package tests;

import io.qameta.allure.*;
import plugins.retry.RetryAnalyzer;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @Owner("Stanislaw")
    @Link("")
    @Description("Тест перехода на страницу оплаты")
    @Epic("SauceDemo checkout")
    @Feature("checkout")
    @Story("Test of checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Позитивный тест страницы оплаты", description = "Проверка перехода на страницу оплаты",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkCheckoutPageIsOpened() {
        loginStep.auth(user,password);
        checkOutStep.isOpened();
    }

    @Owner("Stanislaw")
    @Link("")
    @Description("Позитивный тест оплаты")
    @Epic("SauceDemo checkout test")
    @Feature("checkout test")
    @Story("Test of checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Позитивный тест оплаты", description = "Проверка оплаты пользователем",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkPositiveCheckout() {
        loginStep.auth(user,password);
        checkOutStep.testPositiveCheckout();
    }
}
