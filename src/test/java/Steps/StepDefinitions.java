package Steps;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.SearchPage;
import Utils.BrowserManager;
import Utils.EnvProps;
import Utils.TestDataReader;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class StepDefinitions {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    SearchPage searchPage;
    Scenario scenario;
    WebElement element;
    Actions action;
    HashMap<String , String> data;


    public StepDefinitions(BrowserManager browserManager)
    {
        this.driver=browserManager.getDriver();
    }

    @Before(order = 1)
    public void before(Scenario scenario)
    {
        this.scenario=scenario;
    }

    String url;
    //User open browser and search for facebook login page
    @Given("user open facebook login page")
    public void userOpenFacebookLoginPage() {
        url= EnvProps.getValue("url");
        driver.get(url);
    }

    //user enter login username and password credentials
    @When("the user enter login credentials")
    public void theUserEnterLoginCredentials() {
        loginPage=new LoginPage(driver);
        loginPage.getUsername().sendKeys("8007391825");
        loginPage.getPassword().sendKeys("India@11");
        loginPage.getLoginButton().click();
    }

    //user logged in to facebook website successfully.
    @Then("the user logged in to facebook website successfully")
    public void theUserLoggedInToFacebookWebsiteSuccessfully() {
        homePage=new HomePage(driver);
        Wait wait=new FluentWait(driver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.textMatches(
                By.xpath("(//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6\"])[14]"),
                Pattern.compile("Harshada Marne")));
        String user=homePage.getUser().getText();
        Assert.assertEquals(user,"Harshada Marne");
    }

    //user is on home page
    @Given("the user is on home page")
    public void theUserIsOnHomePage() {
        homePage=new HomePage(driver);
        String user=homePage.getUser().getText();
        Assert.assertEquals(user,"Harshada Marne");
    }
    boolean iconPresent;
    Point points;

    //user navigate to search bar
    @When("user navigate to search bar")
    public void userNavigateToSearchBar() {
        homePage=new HomePage(driver);
        iconPresent=homePage.getSearchInputBox().isDisplayed();
        points =homePage.getSearchInputBox().getLocation();
    }

    //search bar should be displayed to user
    @Then("the search bar should be displayed to user")
    public void theSearchBarShouldBeDisplayedToUser() {
        Assert.assertEquals(iconPresent,true);
        System.out.println(points.getX());
        System.out.println(points.getY());
        Assert.assertEquals(points.getX(),92);
        Assert.assertEquals(points.getY(),8);
    }

    //user move to search bar
    @Given("the user move to search bar")
    public void theUserMoveToSearchBar() {
        homePage=new HomePage(driver);
        String user=homePage.getUser().getText();
        Assert.assertEquals(user,"Harshada Marne");
        element=homePage.getSearchInputBox();
        action=new Actions(driver);
        action.moveToElement(element).perform();
        data= TestDataReader.getData(scenario.getName());
    }

    String spellCheck;
    String value;
    String type;

    //user enter text in search field
    @When("user enter a text in search field")
    public void userEnterATextInSearchField() {
        homePage=new HomePage(driver);
        homePage.getSearchInputBox().sendKeys(data.get("Data"));
        spellCheck=homePage.getSearchInputBox().getAttribute("spellcheck");
        value=homePage.getSearchInputBox().getAttribute("value");
        type=homePage.getSearchInputBox().getAttribute("type");
    }

    //spelling of text should be checked
    @Then("spelling of text should be checked")
    public void spellingOfTextShouldBeChecked() {
        Assert.assertEquals(spellCheck,"true");
        Assert.assertEquals(value.length(),10);
        Assert.assertEquals(type,"text");
    }

    //user navigate to home page
    @Given("the user navigate to home page")
    public void theUserNavigateToHomePage() {
        homePage=new HomePage(driver);
        String user=homePage.getUser().getText();
        Assert.assertEquals(user,"Harshada Marne");
    }

    //user move to search bar
    @And("move to search bar")
    public void moveToSearchBar() {
        homePage=new HomePage(driver);
        element=homePage.getSearchInputBox();
        action=new Actions(driver);
        action.moveToElement(element).perform();
    }

    //user click to search bar
    @When("user click to search bar")
    public void userClickToSearchBar() {
        homePage.getSearchInputBox().click();

        Wait wait=new FluentWait(driver)
                .withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(homePage.getRelatedSuggesstionBox()));
    }

    //recent searches should be displayed to user
    @Then("recent searches should be displayed to user")
    public void recentSearchesShouldBeDisplayedToUser() {
        String text=homePage.getRelatedSuggesstionBox().getText();
        Assert.assertEquals(text,"Recent searches");
    }

    //user clicked on to search bar
    @Given("the user click to search bar")
    public void theUserClickToSearchBar() {
        homePage=new HomePage(driver);
        searchPage=new SearchPage(driver);
        element=homePage.getSearchInputBox();
        action=new Actions(driver);
        action.moveToElement(element).perform();
        homePage.getSearchInputBox().click();
        data= TestDataReader.getData(scenario.getName());
    }

    //user enter text and press Enter key
    @When("user enter a text and press ENTER key")
    public void userEnterATextAndPressENTERKey() {
        homePage.getSearchInputBox().sendKeys(data.get("Data"));
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//div[@class=\"xwoyzhm x1rhet7l\"])[2]//h1")));
    }

    //search results should be displayed
    @Then("search results should be displayed")
    public void searchResultsShouldBeDisplayed() {
        boolean resultPresent=searchPage.getResultText().isDisplayed();
        Assert.assertEquals(resultPresent, true);
    }

    //user move and click to search bar
    @Given("the user move and click to search bar")
    public void theUserMoveAndClickToSearchBar() {
        homePage=new HomePage(driver);
        element=homePage.getSearchInputBox();
        action=new Actions(driver);
        action.moveToElement(element).perform();
        homePage.getSearchInputBox().click();
        data= TestDataReader.getData(scenario.getName());
    }

    //user enter and search for a person
    @When("user enter and search for a person")
    public void userEnterAndSearchForAPerson() {
        homePage.getSearchInputBox().sendKeys(data.get("Data"));
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")));
    }

    //relevant search results should be displayed
    @Then("relevant search results should be displayed")
    public void relevantSearchResultsShouldBeDisplayed() {
        searchPage=new SearchPage(driver);
        List<WebElement> searchResults=searchPage.getSearchResults();
        String expectedString="Omkar ";
        for(int i=0;i<searchResults.size();i++)
        {
            Assert.assertTrue(searchResults.get(i).getText().contains(expectedString));
        }
    }

    //user enter and search for person
    @Given("the user enter and search for person")
    public void theUserEnterAndSearchForPerson() {
        homePage=new HomePage(driver);
        searchPage=new SearchPage(driver);
        data= TestDataReader.getData(scenario.getName());
        homePage.getSearchInputBox().sendKeys(data.get("Data"));
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[2]")));

    }

    //search result page is displayed to user
    @When("search result page displayed to user")
    public void searchResultPageDisplayedToUser() {
        searchPage=new SearchPage(driver);
        searchPage.getSearchResultText().isDisplayed();
        List<WebElement> searchResults=searchPage.getSearchResults();
    }

    //filter options should be displayed to user
    @Then("filters options should be displayed")
    public void filtersOptionsShouldBeDisplayed() {
        boolean present=searchPage.getFilterText().isDisplayed();
        Assert.assertEquals(present,true);
    }

    //user enter and search for person
    @Given("user enter and search for person")
    public void userEnterAndSearchForPerson() {
        homePage=new HomePage(driver);
        data= TestDataReader.getData(scenario.getName());
        homePage.getSearchInputBox().sendKeys(data.get("Data"));
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[2]")));

    }

    //user click to people filter
    @When("user click to people filter")
    public void userClickToPeopleFilter() throws InterruptedException {
        searchPage=new SearchPage(driver);
        searchPage.getSearchResultText().isDisplayed();
        List<WebElement> searchResults=searchPage.getSearchResults();
        Thread.sleep(2000);
        searchPage.getPeopleFilter().click();
    }

    //search result page displayed according to people filter
    @Then("search result page displayed according to people filter")
    public void searchResultPageDisplayedAccordingToPeopleFilter() {
        new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class=\"x193iq5w x1xwk8fm\"]"))));
        boolean peopleGrid=searchPage.getPeopleGrid().isDisplayed();
        Assert.assertEquals(peopleGrid,true);
    }

    //user search for person
    @Given("user search for person")
    public void userSearchForPerson() {
        homePage=new HomePage(driver);
        data= TestDataReader.getData(scenario.getName());
        homePage.getSearchInputBox().sendKeys(data.get("Data"));
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[2]")));
    }

    List<WebElement> friendList;

    //user navigate to search results
    @When("user navigates to search results")
    public void userNavigatesToSearchResults() {
        searchPage=new SearchPage(driver);
        searchPage.getSearchResultText().isDisplayed();
        List<WebElement> searchResults=searchPage.getSearchResults();
        friendList=searchPage.getFriendList();
    }

    //message button should be displayed for people who are friend of user
    @Then("message button should be displayed for people who are friend of user")
    public void messageButtonShouldBeDisplayedForPeopleWhoAreFriendOfUser() {
        System.out.println(friendList.size());
        for(int i=0;i<friendList.size();i++)
        {
            boolean messageButtonStatus=searchPage.getMessageButton().isDisplayed();
            Assert.assertEquals(messageButtonStatus,true);
        }
    }

    //user search for desire person
    @Given("user search for desire person")
    public void userSearchForDesirePerson() {
        homePage=new HomePage(driver);
        searchPage=new SearchPage(driver);
        data= TestDataReader.getData(scenario.getName());
        homePage.getSearchInputBox().sendKeys(data.get("Data"));
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[2]")));
    }

    List<WebElement> notFriendList;

    //search results are displayed to user
    @When("search results is displayed")
    public void searchResultsIsDisplayed() {
        searchPage.getSearchResultText().isDisplayed();
        List<WebElement> searchResults=searchPage.getSearchResults();
        notFriendList=searchPage.getNotFriendList();
    }

    //add friend button should be displayed for people who are not a friend of user
    @Then("add friend button should be displayed for people who are not a friend of user")
    public void addFriendButtonShouldBeDisplayedForPeopleWhoAreNotAFriendOfUser() {
        System.out.println(notFriendList.size());
        for(int i=0;i< notFriendList.size();i++)
        {
            boolean addFriendButtonStatus=searchPage.getAddFriendButton().isDisplayed();
            Assert.assertEquals(addFriendButtonStatus,true);
        }
    }

    //user search for desire person by full name
    @Given("user search for a desire person by {string}")
    public void userSearchForADesirePersonBy(String searchText) {
        homePage=new HomePage(driver);
        searchPage=new SearchPage(driver);
        homePage.getSearchInputBox().sendKeys(searchText);
        homePage.getSearchInputBox().sendKeys(Keys.ENTER);
        new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By
                .xpath("(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[2]")));
    }

    //user click and select desire person name
    @When("user click and select the a desire person name")
    public void userClickAndSelectTheADesirePersonName() throws InterruptedException {
        searchPage.getSearchResultText().isDisplayed();
        new WebDriverWait(driver,20).until(ExpectedConditions
                .visibilityOf(searchPage.getResultbox()));
        searchPage.getResultbox().isDisplayed();
        new WebDriverWait(driver,20).until(ExpectedConditions
                .visibilityOf(searchPage.getDesirePerson()));
        searchPage.getDesirePerson().click();
        new WebDriverWait(driver,20).until(ExpectedConditions
                .visibilityOf(searchPage.getDesirePersonProfilePage()));
    }

    //person name should be displayed on selected person's profile page
    @Then("{string} should be displayed on selected person's profile page")
    public void shouldBeDisplayedOnSelectedPersonSProfilePage(String personName) {
        String searchPerson=searchPage.getDesirePersonProfilePage().getText();
        String expectedPerson=personName;
        Assert.assertTrue(searchPerson.equalsIgnoreCase(expectedPerson));
    }

}
