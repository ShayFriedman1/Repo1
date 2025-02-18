package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//span[@class='base']")
    WebElement MessageText;

    public String getConfirmationMessage() {
        waitForSuccessMessage();
        String successMessage = MessageText.getText();

        return successMessage;
    }

}

