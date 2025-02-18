package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AbstractComponents {

    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    By checkbox = By.xpath("//td/input[@class='radio']");
    By proceedToCheckoutBtn = By.xpath("//li[@class='item'] //button[@type='button']");
    By loaderSign = By.xpath("//div[@class='loader']");

    @FindBy(xpath="//button[@class='button action continue primary']")
    WebElement nextButton;


    public void waitForProceedToCheckoutBtnToAppear(){
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutBtn));
    }

    public void waitForRadioBtnToBeChecked(){
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.elementToBeSelected(checkbox));
        nextButton.click();
    }

    public void waitForLoaderSignToDisappear(){
       WebDriverWait wait = new WebDriverWait(driver, 4);
       wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderSign));
    }

    public void waitForSuccessMessage(){
        WebDriverWait wait = new WebDriverWait(driver, 4);
        wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/checkout/onepage/success/"));
    }

}

