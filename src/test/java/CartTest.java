import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String product = driver.findElement(By.xpath("//span[@data-test='title']"))
                .getText();
        Assert.assertEquals(product, "Products");
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        String removeButton = driver.findElement(By.xpath("//*[@id='remove-sauce-labs-backpack']"))
                .getText();
        Assert.assertEquals(removeButton, "Remove");
        driver.findElement(By.xpath("//*[@id='shopping_cart_container']")).click();
        String yourCart = driver.findElement(By.xpath("//span[@class='title']"))
                .getText();
        Assert.assertEquals(yourCart, "Your Cart");
        driver.findElement(By.xpath("//*[@id='checkout']")).click();
        String checkOut = driver.findElement(By.xpath("//span[@class='title']"))
                .getText();
        Assert.assertEquals(checkOut, "Checkout: Your Information");
        driver.findElement(By.xpath("//*[@id='first-name']")).sendKeys("Stanislaw");
        driver.findElement(By.xpath("//*[@id='last-name']")).sendKeys("Holovnev");
        driver.findElement(By.xpath("//*[@id='postal-code']")).sendKeys("12345");
        driver.findElement(By.xpath("//*[@id='continue']")).click();
        String backpack = driver.findElement(By.xpath("//*[@id='item_4_title_link']")).getText();
        Assert.assertEquals(backpack, "Sauce Labs Backpack");
        String price = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();
        Assert.assertEquals(price, "$29.99");
    }
}
