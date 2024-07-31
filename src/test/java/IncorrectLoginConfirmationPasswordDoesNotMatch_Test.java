import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import java.time.Duration;

public class IncorrectLoginConfirmationPasswordDoesNotMatch_Test {
    @Test
    public void shouldNotRegisterWhenConfirmationPasswordDoesNotMatch() {
        //Arrange
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:4444");
        //Act
        driver.findElement(By.linkText("Register as a new user?")).click();
        driver.findElement(By.id("Email")).click();
        driver.findElement(By.id("Email")).sendKeys("test@test.com");
        driver.findElement(By.id("Password")).click();
        driver.findElement(By.id("Password")).sendKeys("Test1!");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Test12");
        driver.findElement(By.cssSelector("form")).click();
        //Assert
        WebElement confirmError = driver.findElement(By.id("ConfirmPassword-error"));
        Assert.assertTrue(confirmError.isDisplayed());
        Assert.assertTrue(confirmError.getText().contains("The password and confirmation password do not match."));
        driver.quit();
    }
}
