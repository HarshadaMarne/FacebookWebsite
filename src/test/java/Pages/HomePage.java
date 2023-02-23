package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6\"])[14]")
    //(//span[@class="x1lliihq x6ikm8r x10wlt62 x1n2onr6 xlyipyv xuxw1ft"])[5]
    private WebElement user;

    @FindBy(xpath = "(//input[@type=\"search\"])[1]")
    private WebElement searchInputBox;

    @FindBy(xpath = "(//span[@class=\"x1lliihq x6ikm8r x10wlt62 x1n2onr6 x1j85h84\"])[1]")
    private WebElement relatedSuggesstionBox;

    public WebElement getUser() {
        return user;
    }

    public WebElement getSearchInputBox() {
        return searchInputBox;
    }

    public WebElement getRelatedSuggesstionBox() {
        return relatedSuggesstionBox;
    }
}
