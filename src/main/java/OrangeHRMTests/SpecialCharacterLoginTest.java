package OrangeHRMTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class SpecialCharacterLoginTest {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

            usernameField.sendKeys("!@#$%^&*()");

            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("admin123");

            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();

            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Invalid credentials')]")));
            if (errorMessage.isDisplayed()) {
                System.out.println("Test Passed: Error message displayed for special characters in username.");
            } else {
                System.out.println("Test Failed: No error message for special characters in username.");
            }

        } catch (Exception e) {
            System.out.println("Test Failed due to exception: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

