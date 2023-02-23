package Steps;

import Utils.BrowserManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.bs.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    private WebDriver driver;
    private BrowserManager browserManager;

    public Hooks(BrowserManager browserManager)
    {
        this.browserManager=browserManager;
    }
    @Before(order = 0)
    public void setUp(){
        browserManager.setDriver();
    }

    @After(order = 1)
    public void takeScreenShot(Scenario scenario){
        if(scenario.isFailed())
        {
            TakesScreenshot ts=(TakesScreenshot) browserManager.getDriver();
            byte[] src=ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(src,"image/png","screenshot");
        }
    }
    @After(order = 0)
    public void tearDown(){
        browserManager.getDriver().quit();
    }
}
