package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "Steps", publish = true,
        plugin = {"pretty","html:target/cucumber-reports.html"},
        monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

//    @Override
    @DataProvider()
    public Object[][] scenarios(){
        return super.scenarios();
    }
}
