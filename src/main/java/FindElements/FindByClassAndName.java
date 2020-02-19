package FindElements;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
Find element by Class and Name
Using partial link text
Getting text from a WebContent
 */

public class FindByClassAndName{

    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @AfterEach
    public void driverQuitandClose(){
       // driver.close(); //close browser
        driver.quit(); //close session
    }

    @Test
    public void compareTitlePrice() throws Exception{
        driver.get("https://www.amazon.com/");
        driver.findElement(By.name("field-keywords")).sendKeys("Witcher 3"); //entering text
        driver.findElement(By.className("nav-input")).click();
        Thread.sleep(2000);

        driver.findElement(By.partialLinkText("Wild Hunt")).click(); //partialLinkText is static, link is dynamic and long
        Thread.sleep(5000);
        String expectedProductTitle = "Witcher 3: Wild Hunt - Nintendo Switch";

        String actualProductTitle = driver.findElement(By.id("productTitle")).getText(); //retrieving the text
        String actualProductPrice = driver.findElement(By.id("new-button-price")).getText();

        Assertions.assertEquals(expectedProductTitle, actualProductTitle, "Name of the product title is different than expected one");

        System.out.println("Actual product price is: " + actualProductPrice);
    }
}


