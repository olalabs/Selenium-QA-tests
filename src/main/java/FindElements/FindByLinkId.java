package FindElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindByLinkId {

    /*
    Finding WebElements using ID attribute
    Working with links
    Navigate & Fill the form
     */

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.actitime.com/");
        driver.findElement(By.linkText("TRY FREE")).click(); //use the letter case visible on the page, not in the document

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://www.actitime.com/free-online-trial"));

        driver.findElement(By.id("first-name")).sendKeys("Mariola");
        driver.findElement(By.id("last-name")).sendKeys("Wojcik");
        driver.findElement(By.id("email")).sendKeys("mwojcik95@gmail.com");
        driver.findElement(By.id("company")).sendKeys("Student");

    }
}
