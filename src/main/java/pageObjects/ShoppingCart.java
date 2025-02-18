package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.lang.Thread;
import java.util.List;

public class ShoppingCart extends AbstractComponents {


    WebDriver driver;

    public ShoppingCart (WebDriver driver){

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath="//input[@name='firstname']")
    WebElement firstNameElement;
    @FindBy(xpath="//input[@name='lastname']")
    WebElement lastNameElement;
    @FindBy(xpath="//input[@name='street[0]']")
    WebElement addressElement;
    @FindBy(xpath="//input[@name='city']")
    WebElement cityElement;
    @FindBy(xpath="//input[@name='postcode']")
    WebElement postCodeElement;
    @FindBy(xpath="//input[@name='telephone']")
    WebElement telephoneElement;
    @FindBy(xpath = "//fieldset[@id='customer-email-fieldset'] //input[@id='customer-email']")
    WebElement customerEmail;
    @FindBy(css = "table#shopping-cart-table strong.product-item-name")
    List<WebElement> cartProductsNames;


    public void goToShoppingCart (){
    driver.get("https://magento.softwaretestingboard.com/checkout/cart/");
   }


   public float orderTotal (){

        String totalStr = driver.findElement(By.xpath("//tr[@class='grand totals']/td/strong/span")).getText();
        String [] array1 = totalStr.split("\\$");
        String str1 = array1[1];
        Float total = Float.parseFloat(str1);

        return total;
    }


    public CheckOut proceedToCheckout(String Uemail, String Ufirst, String Ulast, String Uaddress, String Ucity, String Upostcode,String Ucountry, String Utelephone) throws InterruptedException {

        waitForProceedToCheckoutBtnToAppear();
        pressOnCheckOutBtn();
        customerEmail.sendKeys(Uemail);
        firstNameElement.sendKeys(Ufirst);
        lastNameElement.sendKeys(Ulast);
        addressElement.sendKeys(Uaddress);
        cityElement.sendKeys(Ucity);
        postCodeElement.sendKeys(Upostcode);
        Thread.sleep(1000);
        Select drpCountry = new Select(driver.findElement(By.xpath("//select[@name='country_id']")));
        drpCountry.selectByVisibleText(Ucountry);
        telephoneElement.sendKeys(Utelephone);
        waitForRadioBtnToBeChecked();

        return new CheckOut(driver);
    }


    public void pressOnCheckOutBtn(){
        driver.findElement(By.xpath("//button[@class='action primary checkout']/span")).click();
    }

    public boolean verifyOrderDisplay(String productName){

        Boolean match = cartProductsNames.stream().anyMatch(product ->product.getText().equalsIgnoreCase(productName));
        return match;
    }


}
