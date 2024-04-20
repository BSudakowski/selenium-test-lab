import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Lab_6_IncorrectPassword_Test {

    @Test
    public void incorrectPasswordTest (){
        //Arrange
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://teja.zarz.agh.edu.pl");
        //Act
        WebElement emailTxt = driver.findElement(By.cssSelector("#Email"));
        emailTxt.sendKeys("test@test.com");
        WebElement passwordTxt = driver.findElement(By.cssSelector("#Password"));
        passwordTxt.sendKeys("Tes");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();
        //Assert
        List<WebElement> validationErrors = driver.findElements(By.cssSelector(".validation-summary-errors>ul>li"));
        boolean doesErrorExists = false;
        doesErrorExists = validationErrors
                .stream()
                .anyMatch(validationError -> validationError.getText().equals("Invalid login attempt."));
        Assert.assertTrue(doesErrorExists);
    }
}
