package XPathTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
XPath  - a query language which is used to find a node or a set of a nodes in XML/HTML document.

Relative XPath
Syntax: //TagName[@Attribute Name='Attribute Value']
*/

public class FindStaticDynamicByXPath {

    private WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void driverQuitAndClose(){
        driver.close();
        driver.quit();
    }

    @Test
    public void getDynamicElements(){
/*
1. GET DYNAMIC ELEMENTS using 'contains', 'starts-with', 'and', 'or' (example of hybrid XPath below)
Syntax:  //TagName[contains(text(),'Partial Value') and starts-with(text(),'Starting Value')]
        //TagName[contains(@Att,'Partial Value') or starts-with(@Att,'Starting Value')]

2. When the tag itself is dynamic
Syntax: //*[@Att='Value']
 */

        driver.navigate().to("https://www.nasa.gov/");
        driver.navigate().to("https://www.goodreads.com/");
        driver.navigate().back();

        String expectedPageTitle = "NASA";
        Assertions.assertEquals(expectedPageTitle,driver.getTitle(), "The title of the page is not:" + expectedPageTitle);

        driver.navigate().forward();
        driver.findElement(By.xpath(".//input[contains(@placeholder,'Title / Author')]")).sendKeys("Charlie");

        driver.findElement(By.xpath(".//img[starts-with(@title,'Title') or alt='search']")).click();
        String entryURL = "https://www.goodreads.com/search?utf8=%E2%9C%93&query=Charlie";
        Assertions.assertEquals(entryURL, driver.getCurrentUrl());
    }

    @Test
    public void getStaticElements(){ //Example, not test method indeed

        driver.get("https://www.facebook.com/");
        //By attributes. Syntax: .//TagName[@Attribute Name=”Attribute Value”]
        driver.findElement(By.xpath(".//input[@name='firstname']")).sendKeys("Asdf");
        //By text - select radio button. Syntax: .//TagName[text()=”Text”]
        driver.findElement(By.xpath(".//label[text()='Kobieta']")).click();
    }

}
