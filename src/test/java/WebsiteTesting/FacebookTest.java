package WebsiteTesting;

import Pages.HomePage;
import Pages.SearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.formula.functions.T;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class FacebookTest {
    WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;

    @BeforeTest
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver=new ChromeDriver(options);
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        //Login to facebook
        driver.findElement(By.id("email"))
                .sendKeys("8007391825");
        driver.findElement(By.id("pass"))
                .sendKeys("India@11");

        //Home page accessible after entering valid credentials and clicking on login button
        driver.findElement(By.xpath("(//div[@class=\"_6ltg\"])[1]//button")).click();
        Wait wait=new FluentWait(driver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.textMatches(
                By.xpath("(//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6\"])[14]"),
                Pattern.compile("Harshada Marne")));
        String user=driver.findElement(By.xpath("(//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6\"])[14]"))
                .getText();
        Assert.assertEquals(user,"Harshada Marne");
        homePage=new HomePage(driver);

    }


    @Test(description = "To validate that search icon should be present on home page.", priority = 1)
    public void searchIconTest(){
        boolean iconPresent=homePage.getSearchInputBox().isDisplayed();
        Assert.assertEquals(iconPresent,true);
        Point points =homePage.getSearchInputBox().getLocation();
        System.out.println(points.getX());
        System.out.println(points.getY());
//        Assert.assertEquals(points.getX(),92);
//        Assert.assertEquals(points.getY(),8);
    }

    @Test(description = "To validate that recent searches should be display to user while searching on website.",priority = 2)
    public void relatedSuggestionTest() throws InterruptedException {
//        driver.findElement(
//                By.xpath("(//input[@type=\"search\"])[1]")).click();
        WebElement searchElement=homePage.getSearchInputBox();
        Actions action=new Actions(driver);
        action.moveToElement(searchElement).perform();
        homePage.getSearchInputBox().click();

        Wait wait=new FluentWait(driver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(homePage.getRelatedSuggesstionBox()));
        String text=homePage.getRelatedSuggesstionBox().getText();
        Assert.assertEquals(text,"Recent searches");
    }

    @Test(description = "To validate that user should able to enter & search a text on website.",priority = 3)
    public void enterTextTest() throws InterruptedException {
        //Type text in search input and press Enter key
//        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
//                .sendKeys("omkar");
        homePage.getSearchInputBox().sendKeys("omkar");
//        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
//                .sendKeys(Keys.ENTER);
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")));
        boolean resultPresent=driver.findElement(By
                        .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]"))
                .isDisplayed();
        Assert.assertEquals(resultPresent, true);
    }

    @Test(description = "To validate that search results should be relevant to search text entered by user on website.", priority = 4)
    public void relevantSearchResults() throws InterruptedException {
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys("omkar");
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys(Keys.ENTER);

       Thread.sleep(2000);
        List<WebElement> searchResults=driver.findElements(By
                .xpath("//a[@class=\"x1i10hfl xjbqb8w x6umtig x1b1mbwd xaqea5y xav7gou x9f619 x1ypdohk xt0psk2 xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r xexx8yu x4uap5 x18d9i69 xkhd6sd x16tdsg8 x1hl2dhg xggy1nq x1a2a7pz xt0b8zv xzsf02u x1s688f\"]"));

        System.out.println(searchResults.size());
       for(int i=0;i<searchResults.size();i++)
       {
           System.out.println(searchResults.get(i).getText());
       }
//        System.out.println(searchResults.size());
        Assert.assertEquals(searchResults.size(),10);
    }

    @Test
    public void filtersTextPresentTest(){
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys("omkar");
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")));
        boolean present=driver.findElement(By.xpath("(//h2[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz x193iq5w xeuugli\"])[3]//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6 x1j85h84\"]")).isDisplayed();
        String text=driver.findElement(By.xpath("(//h2[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz x193iq5w xeuugli\"])[3]//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6 x1j85h84\"]")).getText();
        Assert.assertEquals(present,true);
//        Assert.assertEquals(text,"Filters");
    }

    @Test
    public void filterResultTest(){
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys("omkar");
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")));
        driver.findElement(By
                .xpath("(//span[@class=\"x193iq5w xeuugli x13faqbe x1vvkbs x1xmvt09 x6prxxf xvq8zen xk50ysn xzsf02u\"])[3]")).click();
        boolean peopleGrid=driver.findElement(By.xpath("//div[@class=\"x193iq5w x1xwk8fm\"]")).isDisplayed();
        Assert.assertEquals(peopleGrid,true);
    }

    @Test
    public void messageButtonTest(){
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys("omkar");
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")));
        List<WebElement> friendList=driver.findElements(By
                .xpath("//span[contains(text(),'Friend ')]"));
        System.out.println(friendList.size());
        for(int i=0;i<friendList.size();i++)
        {
            boolean messageButtonStatus=driver.findElement(By
                    .xpath("//span[contains(text(),'Message')]")).isDisplayed();
            Assert.assertEquals(messageButtonStatus,true);
        }
    }

    @Test
    public void addFriendButtonTest(){
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys("omkar");
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")));
        List<WebElement> notFriendList=driver.findElements(By
                .xpath("(//div[@class=\"x78zum5 x1n2onr6 xh8yej3\"])[1]//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6\" and not(contains(text(), 'Friend')) ]"));
        System.out.println(notFriendList.size());
        for(int i=0;i< notFriendList.size();i++)
        {
            boolean addFriendButtonStatus=driver.findElement(By
                    .xpath("(//div[@class=\"x78zum5 x1n2onr6 xh8yej3\"])[1]//span[contains(text(),'Add friend')]")).isDisplayed();
            Assert.assertEquals(addFriendButtonStatus,true);
        }
    }

    @Test
    public void profilePageDisplay() throws InterruptedException {
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys("omkar");
        driver.findElement(By.xpath("(//input[contains(@type,\"search\")])[1]"))
                .sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Omkar Naik')]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/omkar.naik.10");
        String searchPerson=driver.findElement(By.xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[4]")).getText();
        Assert.assertEquals(searchPerson,"Omkar Naik");
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}

