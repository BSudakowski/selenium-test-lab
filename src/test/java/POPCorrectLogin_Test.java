import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class POPCorrectLogin_Test {

    private WebDriver driver;

    @Test
    public void correctLoginTest(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost:4444");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeEmail("test@test.com");
        loginPage.typePassword("Test1!");
        HomePage homePage = loginPage.submitLogin();

        Assert.assertTrue(homePage.welcomeElm.isDisplayed(), "Welcome element is not shown.");
        Assert.assertTrue(homePage.welcomeElm.getText().contains("Welcome"), "Welcome element text: '" + homePage.welcomeElm.getText() + "'does not contain word 'Welcome'");

        driver.quit();


    }
}
