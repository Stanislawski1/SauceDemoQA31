package wrappers;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;

public class LogIn {

    WebDriver driver;
    ProductsPage productsPage;

    public LogIn(WebDriver driver) {
        this.driver = driver;
        productsPage = new ProductsPage(driver);
    }


}
