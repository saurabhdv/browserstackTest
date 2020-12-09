package com.browserstack;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task_2_TestBrowserStack extends BrowserStackJUnitTest {
/*    WebDriver driver;
    WebDriverWait wait;*/
    String searchString = "Samsung Galaxy S10";
    String FlipKartURL = "https://www.flipkart.com";
/*    public static final String USERNAME = "saurabhverma20";
    public static final String AUTOMATE_KEY = "V1A8g35e7prDfXKVQeEA";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";*/

    @Before
    public void login() {

       /* System.setProperty("webdriver.chrome.driver", "Browser\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();*/

/*        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80");
        caps.setCapability("name", "saurabhverma20's First Test");*/
      //  try {
           // driver = new RemoteWebDriver(new URL(URL), caps);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.get(FlipKartURL);
      //  } catch (Exception e) {
       //     e.printStackTrace();
       // }

    }

    @Test
    /*
        1.	Using Selenium, load the flipkart.com desktop home page.
        2.	Search for the product "Samsung Galaxy S10" on that page.
        3.	On the search results click on "Mobiles" in categories.
        4.	Apply the following filters (in filters section on the left hand side):
        1.	Brand: Samsung
        2.	Select Flipkart assured
        5.	Sort the entries with Price -> High to Low.
        6.	Read the set of results that show up on page 1.
        7.	Create a list with the following parameters, and print this on the console.
        1.	Product Name
        2.	Display Price
        3.	Link to Product Details Page

       * */
    public void verifyResults() {


        //Not logging for now Escape login
        new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
        driver.findElement(By.name("q")).click();
        driver.findElement(By.name("q")).sendKeys(searchString);
        driver.findElement(By.xpath("//*[@class='L0Z3Pu']")).click();
        //validate result found for search string
        Assert.assertTrue("Not able to find product", driver.findElement(By.cssSelector(" div.E2-pcE._3zjXRo.col-12-12 > div > div > span")).getText().contains("Samsung Galaxy S10"));

        //Applying Filters
        driver.findElement(By.linkText("Mobiles")).click();
        // flipkart assured
        driver.findElement(By.cssSelector("div._24_Dny._3tCU7L")).click();

         try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // high to low
        driver.findElement(By.cssSelector("div._5THWM1 > div:nth-child(5)")).click();

        WebElement element = driver.findElement(By.xpath("(//div[@class='_2iDkf8 t0pPfW']//input)[7]"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //new Actions(driver).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
        new Actions(driver).moveToElement(element).click().perform();

        List<WebElement> listOfProductName = driver.findElements(By.xpath("//*[@class='_4rR01T']"));
        for (int i = 1; i <= listOfProductName.size(); i++) {
            String prd = "(//*[@class='_4rR01T'])[" + i + "]";
            String eachProductName = driver.findElement(By.xpath(prd)).getText();

            String eachDisplayName = driver.findElement(By.xpath("(//*[@class='_4rR01T'])[" + i + "]")).getText();
            String eachHrefs = driver.findElement(By.xpath("(//*[@class='_13oc-S' ]//a)[" + i + "]")).getAttribute("href");

            System.out.println("Product Name =" + eachProductName + "\n" +
                    "Display Price = " + eachDisplayName + "\n" + "Link to Product Details Page =" + eachHrefs + "\n" + "**********");
        }


    }

    @After
    public void quit() {
        driver.quit();
    }

}
