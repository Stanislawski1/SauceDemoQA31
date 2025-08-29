package tests;

import io.qameta.allure.*;
import plugins.retry.RetryAnalyzer;
import org.testng.annotations.Test;
import data_test.DataProvider;
import static org.testng.Assert.assertEquals;

public class ProductTest extends BaseTest {

    @Owner("Stanislaw")
    @Link("")
    @Description("Провекрка входа на страницу товаров")
    @Epic("SauceDemo Log in")
    @Feature("Open product page test")
    @Story("Open product page test")
    @Severity(SeverityLevel.CRITICAL)
    @Lead("Timofei")
    @TmsLink("SD-01")
    @Issue("SD_01/1")
    @Test(testName = "Вход на страницу товаров", description = "Провекрка входа на страницу товаров",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkProductPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.isPageOpened();
    }

    @Test(testName = "Проверка фильтрации продуктов", dataProvider = "filters", dataProviderClass = DataProvider.class,
            retryAnalyzer = RetryAnalyzer.class, description = "Проверка фильтрации продуктов")
    @Owner("Stanislaw")
    @Link("")
    @Description("Проверка фильтрации продуктов")
    @Epic("SauceDemo Sorting")
    @Feature("Sorting")
    @Story("Sorting with positive cred") // История
    @Severity(SeverityLevel.CRITICAL) // Приоритет
    @Lead("Timofei") // team lead
    @TmsLink("SD-01") // Ссылка на Quase
    @Issue("SD_01/1") // Ссылка на баг репорт

    public void checkSorting(String filterOption, String expectedFirstItem) {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.sortingFilters(filterOption);
        String actualFirstItem = productsPage.getFirstProductTitle();
        assertEquals(actualFirstItem, expectedFirstItem,
                "Первый товар после сортировки '" + filterOption + "' не совпадает");
    }
}

