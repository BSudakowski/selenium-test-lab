import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class IncorrectEmail_Test {

    @Test
    public void incorrectLoginTestWrongEmail(){
        //Arrange
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://teja.zarz.agh.edu.pl");
        //Act
        WebElement emailTxt = driver.findElement(By.cssSelector("#Email"));
        emailTxt.sendKeys("test");
        WebElement passwordTxt = driver.findElement(By.cssSelector("#Password"));
        passwordTxt.sendKeys("Test1!");
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();
        WebElement emailError = driver.findElement(By.cssSelector("#Email-error"));
        //Assert
        Assert.assertTrue(emailError.isDisplayed());
        Assert.assertTrue(emailError.getText().contains("The Email field is not a valid e-mail address."));


        List<WebElement> validationErrors = driver.findElements(By.cssSelector(".validation-summary-errors>ul>li"));
        boolean doesErrorExists = false;
        //Old school
        for (int i=0; i<validationErrors.size(); i++){
            if (validationErrors.get(i).getText().equals("The Email field is not a valid e-mail address.")){
                doesErrorExists = true;
                break;
            }
        }
        Assert.assertTrue(doesErrorExists);

        //New school
        doesErrorExists = validationErrors
                .stream()
                .anyMatch(validationError -> validationError.getText().equals("The Email field is not a valid e-mail address."));
        Assert.assertTrue(doesErrorExists);
    }
}
