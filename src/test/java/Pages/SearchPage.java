package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[3]")
    private WebElement resultText;

    @FindBy(xpath = "(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[2]")
    private WebElement searchResultText;

    @FindBy(xpath = "(//div[@class=\"x1xmf6yo\"])[2]//a[@class=\"x1i10hfl xjbqb8w x6umtig x1b1mbwd xaqea5y xav7gou x9f619 x1ypdohk xt0psk2 xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r xexx8yu x4uap5 x18d9i69 xkhd6sd x16tdsg8 x1hl2dhg xggy1nq x1a2a7pz xt0b8zv xzsf02u x1s688f\"]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "(//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6 x1j85h84\"])[3]")
    private WebElement filterText;

    @FindBy(xpath = "(//span[@class=\"x193iq5w xeuugli x13faqbe x1vvkbs x1xmvt09 x6prxxf xvq8zen xk50ysn xzsf02u\"])[3]")
    private WebElement peopleFilter;

    @FindBy(xpath = "//div[@class=\"x193iq5w x1xwk8fm\"]")
    private WebElement peopleGrid;

    @FindBy(xpath = "//span[contains(text(),'Friend ')]")
    private List<WebElement> friendList;

    @FindBy(xpath = "//span[contains(text(),'Message')]")
    private WebElement messageButton;

    @FindBy(xpath = "(//div[@class=\"x78zum5 x1n2onr6 xh8yej3\"])[1]//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6\" and not(contains(text(), 'Friend')) ]")
    private List<WebElement> notFriendList;

    @FindBy(xpath = "(//div[@class=\"x78zum5 x1n2onr6 xh8yej3\"])[1]//span[contains(text(),'Add friend')]")
    private WebElement addFriendButton;

    @FindBy(xpath = "//div[@class=\"xyamay9 x1pi30zi x1l90r2v x1swvt13\"]")
    private WebElement resultbox;

    @FindBy(xpath = "(//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6 x8182xy xwrv7xz x1kgmq87 xmgb6t1 x1h0ha7o xg83lxy x1nn3v0j x1120s5i\"])[1]//a")
    private WebElement desirePerson;

    @FindBy(xpath = "(//h1[@class=\"x1heor9g x1qlqyl8 x1pd3egz x1a2a7pz\"])[4]")
    private WebElement desirePersonProfilePage;

    public WebElement getResultText() {
        return resultText;
    }

    public WebElement getDesirePerson() {
        return desirePerson;
    }

    public WebElement getDesirePersonProfilePage() {
        return desirePersonProfilePage;
    }

    public WebElement getResultbox() {
        return resultbox;
    }

    public WebElement getAddFriendButton() {
        return addFriendButton;
    }

    public List<WebElement> getNotFriendList() {
        return notFriendList;
    }

    public WebElement getMessageButton() {
        return messageButton;
    }

    public List<WebElement> getFriendList() {
        return friendList;
    }

    public WebElement getPeopleGrid() {
        return peopleGrid;
    }

    public WebElement getPeopleFilter() {
        return peopleFilter;
    }

    public WebElement getSearchResultText() {
        return searchResultText;
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public WebElement getFilterText() {
        return filterText;
    }
}
