package Utils;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.SearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManager {

    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;

    public WebDriver getDriver(){
        return driver;
    }
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        homePage=new HomePage(driver);
        loginPage=new LoginPage(driver);
        searchPage=new SearchPage(driver);
    }
}
