package tests;

import plugins.retry.RetryAnalyzer;
import org.testng.annotations.Test;
import plugins.providers.DataProvider;
import static org.testng.Assert.assertEquals;

public class ProductTest extends BaseTest {

    @Test(testName = "Вход на страницу товаров", description = "Провекрка входа на страницу товаров",
            retryAnalyzer = RetryAnalyzer.class)
    public void checkProductPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.open();
        productsPage.isPageOpened();
    }

    @Test(testName = "Проверка фильтрации продуктов", dataProvider = "filters", dataProviderClass = DataProvider.class,
            retryAnalyzer = RetryAnalyzer.class)
    public void checkSorting(String filterOption, String expectedFirstItem) {
        loginPage.open();
        loginPage.login("standard_use", "secret_sauce");
        productsPage.sortingFilters(filterOption);
        String actualFirstItem = productsPage.getFirstProductTitle();
        assertEquals(actualFirstItem, expectedFirstItem,
                "Первый товар после сортировки '" + filterOption + "' не совпадает");
    }
}

