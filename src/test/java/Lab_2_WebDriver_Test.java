import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Lab_2_WebDriver_Test {

    @Test
    public void playWithWebDriver() {
        //Arrange
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.playwright.dev/todomvc");
        //Act
        WebElement toDoTxt = driver.findElement(By.cssSelector(".new-todo"));
        toDoTxt.sendKeys("Naprawić zlew" + Keys.ENTER);
        //Assert
        WebElement expTodoTitleElm = driver.findElement(By.cssSelector("[data-testid=todo-title]"));
        Assert.assertEquals(expTodoTitleElm.getText(), "Naprawić zlew");

        WebElement countElm = driver.findElement(By.cssSelector("[data-testid=todo-count]"));
        Assert.assertEquals(countElm.getText(), "1 item left");

        //Act
        WebElement todoItemChk = driver.findElement(By.cssSelector("[data-testid=todo-item] input"));
        todoItemChk.click();
        WebElement expTodoItemElm = driver.findElement(By.cssSelector("[data-testid=todo-item]"));
        //Assert
        Assert.assertEquals(expTodoItemElm.getAttribute("class"), "completed");
        countElm = driver.findElement(By.cssSelector("[data-testid=todo-count]"));
        Assert.assertEquals(countElm.getText(), "0 items left");

        //Act
        WebElement todoItemDeleteBtn = driver.findElement(By.cssSelector("button.destroy"));
        todoItemDeleteBtn.click();
        List<WebElement> expTodoItems = driver.findElements(By.cssSelector("[data-testid=todo-item"));
        //Assert
        Assert.assertEquals(expTodoItems.size(), 0);

    }
}
