package CSSselectorsTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 **** Creating CSS Selector Using Attributes ****
    Syntax: TagName[Att1=Value]
    In case Att1 is 'id' or 'class' then we can use following symbols:
     		id => # (hash)
     		class => . (dot)
    Examples: 1. div#u123   2. span.layerParent
 */

public class FindBySimplyCssSelector {

    private WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //maximize the browser
    }

    @AfterEach
    public void driverQuitAndClose(){
        driver.close();
        driver.quit();
    }

    @Test
    public  void checkVisability(){

        driver.navigate().to("https://google.com");
        WebElement searchField = driver.findElement(By.cssSelector("[title='Szukaj']"));  //searchField variable of the WebElement type
        String searchEntry = "facebook";
        searchField.sendKeys(searchEntry);
        searchField.submit();
        String title = "Facebook – zaloguj się lub zarejestruj";
        driver.findElement(By.xpath(".//*[text()='" + title + "']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5); //explicit wait
        wait.until(ExpectedConditions.titleIs(title));

        //check visability of WebElement on the screen - methods: isDisplayed()  / isEnabled()  / isSelected()
        boolean isLogoDisplayed = driver.findElement(By.cssSelector(".fb_logo")).isDisplayed();
        Assertions.assertTrue(isLogoDisplayed); //assertTrue <- JUnit assertion

    }

    @Test
    public void checkTextAndColour() throws Exception{
        driver.get("https://www.amazon.com/");

        driver.findElement(By.cssSelector("i.hm-icon")).click();   //use CLASS attribute

        driver.findElement(By.partialLinkText("Echo & Alexa")).click();
        Thread.sleep(5000);

        driver.findElement(By.xpath("//div[contains(text(),'Echo Flex')]")).click();
        Thread.sleep(5000);

        String title = driver.findElement(By.cssSelector("span#productTitle")).getText(); //use ID attribute

        System.out.println(title);

        //check if text is present on the page using contains() method
        if (title.contains("Flex")) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        String color = driver.findElement(By.cssSelector("span#productTitle")).getCssValue("color"); //get css properties by getCssValue()
        System.out.println("Color of the text is: " + color);

        String expectedPageTitle = "Amazon.com: Introducing Echo Flex - Plug-in mini smart speaker with Alexa: Amazon Devices";
        Assertions.assertEquals(expectedPageTitle, driver.getTitle()); //get the title of a page; assertEquals <- JUnit assertion


    }
}
