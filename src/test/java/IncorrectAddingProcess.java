import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class IncorrectAddingProcess {

    @Test
    public void addProcessWithFailureTest() {
        String shortProcessName = "ab";
        String expErrorMessage = "The field Name must be a string with a minimum length of 3 and a maximum length of 30.";

        //Arrange
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:4444");
        //Act
        WebElement emailTxt = driver.findElement(By.cssSelector("#Email"));
        emailTxt.sendKeys("test@test.com");
        WebElement passwordTxt = driver.findElement(By.cssSelector("#Password"));
        passwordTxt.sendKeys("Test1!");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();
        driver.findElement(By.linkText("Workspace")).click();
        driver.findElement(By.linkText("Processes")).click();
        driver.findElement(By.linkText("Add new process")).click();
        driver.findElement(By.id("Name")).sendKeys(shortProcessName);
        driver.findElement(By.id("Description")).sendKeys("test123");
        driver.findElement(By.id("Notes")).sendKeys("test123");
        driver.findElement(By.cssSelector("input[type=submit]")).click();
        //Assert
        WebElement pageConfirm = driver.findElement(By.cssSelector(".field-validation-error"));
        Assert.assertTrue(pageConfirm.isDisplayed());
        Assert.assertTrue(pageConfirm.getText().contains(expErrorMessage));
        driver.quit();

    }
    }
