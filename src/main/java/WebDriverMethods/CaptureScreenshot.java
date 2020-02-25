package WebDriverMethods;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class CaptureScreenshot {
    private WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295,730)); //set browser size and position
        driver.manage().window().setPosition(new Point(10,40));
    }

    @AfterEach
    public void driverQuitAndClose(){
        driver.close();
        driver.quit();
    }

    @Test
    public void takeItForSuccessfulCase() throws IOException {
        try {
            driver.get("http://the-internet.herokuapp.com/login");
            driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
            driver.findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();

            driver.findElement(By.xpath("//a[@class='button secondary radius']")).click();

            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("D:\\LogOutSuccess.png"));

            String actualSuccessAlert = driver.findElement(By.xpath("//div[@id='flash']")).getText();

            String expectedAlert = "You logged out of the secure area!\n" + "×";
            Assertions.assertEquals(expectedAlert, actualSuccessAlert);

            if (actualSuccessAlert.contains("logged out")) {
                System.out.println("Text displayed in the alert: " + actualSuccessAlert + "\nAssertions are equal. Test pass.");
            } else {
                System.out.println("Fail");
            }

        }catch(Exception e){
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("D:\\LogOutFailed.png"));
        }

    }

    @Test
    public void takeItForFailedCase() throws IOException {
        try{

            driver.get("http://the-internet.herokuapp.com/login");
            driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
            driver.findElement(By.cssSelector("#password")).sendKeys("Super!");
            driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
            boolean isValidation = driver.findElement(By.cssSelector("#flash")).isDisplayed();
            Assertions.assertTrue(isValidation);

            driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");

            //find the element with wrong xpath, so control moves to catch block
            driver.findElement(By.cssSelector("#wrongSelector")).sendKeys("Testing");

        }catch(Exception e){
            System.out.println("Wrong Css selector to find password. Screenshot!");
            //takes the screenshot and saves in the path mentioned when test fails
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("D:\\InvalidSelector.png"));
        }
    }
}
