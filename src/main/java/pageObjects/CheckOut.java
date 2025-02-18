package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut extends AbstractComponents{


    WebDriver driver;

    public CheckOut (WebDriver driver){

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[@class='action primary checkout']")
    WebElement placeOrderButton;



     public ConfirmationPage placeTheOrder(){
         waitForLoaderSignToDisappear();
         placeOrderButton.click();

         return new ConfirmationPage(driver);
     }

}
