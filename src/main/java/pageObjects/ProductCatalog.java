package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalog extends AbstractComponents {

    public float theTotal=0;
    float totalOfCart;
    WebDriver driver;


    public ProductCatalog (WebDriver driver){

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void goToProductsView(){

        driver.findElement(By.xpath("//a[@class='logo']")).click();
    }

    public ShoppingCart addToCart(int numberOfElements) throws InterruptedException {

        float calculatedTotal=0;
        for (int i=0;i<numberOfElements;i++) {
            WebElement itemsElement = driver.findElement(By.xpath("//div/ol"));
            itemsElement.findElements(By.xpath("//div[@class='product-item-info'] //div[@option-label='M']")).get(i).click();
            itemsElement.findElements(By.xpath("//div[@class='swatch-option color'][1]")).get(i).click();
            String currentItemPrice = itemsElement.findElements(By.xpath("//span[@class='price']")).get(i).getText();
            calculatedTotal = totalValueOfCart(currentItemPrice);
            itemsElement.findElements(By.xpath("//button[@class='action tocart primary']")).get(i).click();
        }
        theTotal = calculatedTotal;
        return new ShoppingCart(driver);
    }

    public float totalValueOfCart (String itemPrice) throws InterruptedException {

        String [] array1 = itemPrice.split("\\$");
        Thread.sleep(1000);
        String str1 = array1[1];
        Float price = Float.parseFloat(str1);
        totalOfCart=totalOfCart+price;

        return totalOfCart;
    }






}
