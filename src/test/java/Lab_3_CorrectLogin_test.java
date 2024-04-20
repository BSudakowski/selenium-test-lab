import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Lab_3_CorrectLogin_test {

    @Test
    public void correctLoginTest(){
        //Arrange
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://teja.zarz.agh.edu.pl");
        //Act
        WebElement emailTxt = driver.findElement(By.cssSelector("#Email"));
        emailTxt.sendKeys("test@test.com");
        WebElement passwordTxt = driver.findElement(By.cssSelector("#Password"));
        passwordTxt.sendKeys("Test1!");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();
        WebElement welcomeElm = driver.findElement(By.cssSelector(".profile_info"));
        //Assert
        Assert.assertTrue(welcomeElm.isDisplayed());
        Assert.assertTrue(welcomeElm.getText().contains("Welcome"));

        driver.quit();


    }
}
