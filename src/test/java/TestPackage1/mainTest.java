package TestPackage1;

import TestComponents.Retry;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import pageObjects.*;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class mainTest extends BaseTest {


    @Test(dataProvider = "getData" )
    public void createNewAccount(HashMap<String,String> input)  {
    // using data provider with json file

        // launchApplication() is executed by beforeMethod
        ProductCatalog productCatalog = landingPage.createNewAccount("Shay", "Friedman", input.get("password"), input.get("email"));
        productCatalog.goToProductsView();
    }


    // using data provider with json file
    @Test(dependsOnMethods = {"createNewAccount"},dataProvider = "getData",retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
// launchApplication() is executed by beforeMethod
        ProductCatalog productCatalog = landingPage.login(input.get("password"), input.get("email"));
        productCatalog.goToProductsView();
        ShoppingCart shoppingCart = productCatalog.addToCart(2);

        float referenceCalculatedTotalPrice = productCatalog.theTotal;
        shoppingCart.goToShoppingCart();
        float orderTotalFromCart = shoppingCart.orderTotal();
        Assert.assertTrue(shoppingCart.verifyOrderDisplay("Radiant Tee"));

        Assert.assertEquals(referenceCalculatedTotalPrice, orderTotalFromCart, "The total values do not mach");
        CheckOut checkout = shoppingCart.proceedToCheckout("abcd12345@gmail.com", "Shay", "Friedman", "GolanyST", "Modiin", "123456789", "Israel", "0544772262");
        ConfirmationPage confirmationPage = checkout.placeTheOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(confirmMessage, "Thank you for your purchase!", "There is a problem with the order");
    }



    @DataProvider
    public Object [][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Data//data.json");
        //return new Object [][] {{data.get(0)}, {data.get(1)}};
        return new Object [][] {{data.get(0)}};
    }




}