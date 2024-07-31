import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CorrectAddCharacteristicDemoProjectCharacteristicTable_Test {

    @Test
    public void CorrectAddCharacteristicDemoProjectCharacteristicTable() {
        String characteristicName = "NameTest";
        String lsl = "8";
        String usl = "10";

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
        driver.findElement(By.linkText("Characteristics")).click();
        driver.findElement(By.linkText("Add new characteristic")).click();
        driver.findElement(By.id("ProjectId")).click();
        {
            WebElement dropdown = driver.findElement(By.id("ProjectId"));
            dropdown.findElement(By.xpath("//option[. = 'DEMO PROJECT']")).click();
        }
        driver.findElement(By.id("Name")).click();
        driver.findElement(By.id("Name")).sendKeys(characteristicName);
        driver.findElement(By.id("LowerSpecificationLimit")).sendKeys(lsl);
        driver.findElement(By.id("UpperSpecificationLimit")).sendKeys(usl);
        driver.findElement(By.id("HistogramBinCount")).sendKeys("13");
        driver.findElement(By.cssSelector(".btn-success")).click();
        //Assert
        driver.findElement(By.linkText("Home")).click();
        driver.findElement(By.linkText("Dashboard")).click();
        driver.findElement(By.xpath("//h2[text()='DEMO PROJECT']"));
        WebElement characteristicsView = driver.findElement(By.cssSelector(".x_content"));
        Assert.assertTrue(characteristicsView.getText().contains(characteristicName));

    }
}
