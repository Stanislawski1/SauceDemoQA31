package plugins.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureUtils {

    public static void takeScreenshot(WebDriver driver) {
        try {
            // Проверяем, что тест запущен
            if (Allure.getLifecycle().getCurrentTestCase().isPresent()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle().addAttachment(
                        "Screenshot",
                        "image/png",
                        "png",
                        screenshot
                );
            } else {
                System.out.println("WARN: Cannot take screenshot - no test is running");
            }
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}