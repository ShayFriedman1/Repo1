package pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage extends AbstractComponents {


    WebDriver driver;

    public LandingPage (WebDriver driver){

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="email")
    WebElement emailElement;
    @FindBy(id="pass")
    WebElement passwordElement;
    @FindBy(xpath="//button[@name='send']")
    WebElement signIn;

    @FindBy(id="firstname")
    WebElement firstNameElement;
    @FindBy(id="lastname")
    WebElement lastNameElement;
    @FindBy(id="email_address")
    WebElement emailaddressElement;
    @FindBy(id="password")
    WebElement passwordelement;
    @FindBy(id="password-confirmation")
    WebElement passwordconfirmationElement;
    @FindBy(xpath="//ul[@class='header links']/li[3]")
    WebElement createNewAccount;
    @FindBy(xpath="//button[@class='action submit primary']")
    WebElement submitPrimary;
    @FindBy(xpath="//ul[@class='header links']/li[2]")
    WebElement signInPrimary;


    public ProductCatalog login (String userEmail,String userPassword) {

        signInPrimary.click();
        emailElement.sendKeys(userEmail);
        passwordElement.sendKeys(userPassword);
        signIn.click();

        return new ProductCatalog(driver);
    }

    public ProductCatalog createNewAccount(String firstName, String lastName, String password, String email){

        createNewAccount.click();
        firstNameElement.sendKeys(firstName);
        lastNameElement.sendKeys(lastName);
        emailaddressElement.sendKeys(email);
        passwordelement.sendKeys(password);
        passwordconfirmationElement.sendKeys(password);
        submitPrimary.click();

        return new ProductCatalog(driver);
    }


    public  void goTo(){
        driver.get("https://magento.softwaretestingboard.com");
    }


}
