package electronics;
// **** Created By Harshit Patel ****

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

import java.util.Random;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        //1.1 Mouse Hover on “Electronics” Tab
        mouseHoverToElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/a[1]"));

        //1.2 Mouse Hover on “Cell phones” and click
        mouseHoverToElementAndClick(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));

        //1.3 Verify the text “Cell phones”
        String expectedCellPhonePage = "Cell phones";
        String actualCellPhonePage = getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        Assert.assertEquals(actualCellPhonePage, expectedCellPhonePage);

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        //2.1 Mouse Hover on “Electronics” Tab
        mouseHoverToElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/a[1]"));

        //2.2 Mouse Hover on “Cell phones” and click
        mouseHoverToElementAndClick(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));

        //2.3 Verify the text “Cell phones”
        String expectedCellPhonePage1 = "Cell phones";
        String actualCellPhonePage1 = getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        Assert.assertEquals(actualCellPhonePage1, expectedCellPhonePage1);

        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));

        //2.5 Click on product name “Nokia Lumia 1020” link
        Thread.sleep(5000);
        clickOnElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"));

        //2.6 Verify the text “Nokia Lumia 1020”
        String expectedNokiaL1020 = "Nokia Lumia 1020";
        String actualNokiaL1020 = getTextFromElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        Assert.assertEquals(actualNokiaL1020, expectedNokiaL1020);

        //2.7 Verify the price “$349.00”
        String expectedPrice = "$349.00";
        String actualPrice = getTextFromElement(By.xpath("//span[@id='price-value-20']"));
        Assert.assertEquals(actualPrice, expectedPrice);

        //2.8 Change quantity to 2
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).clear();
        sendTextToElement(By.xpath("//input[@id='product_enteredQuantity_20']"), "2");

        //2.9 Click on “ADD TO CART” tab
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        System.out.println("Actual Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);

        //After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class='close']"));

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        //2.12 Verify the message "Shopping cart"
        String expectedShoppingCartMessage = "Shopping cart";
        String actualShoppingCartMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals(actualShoppingCartMessage, expectedShoppingCartMessage);
        System.out.println("Nevigated to Shopping cart Message: " + actualShoppingCartMessage);

        Thread.sleep(5000);

        //2.13 Verify the quantity is 2
        String expectedQty = "2";
        String actualQty = getTextFromElement(By.xpath("//span[contains(text(),'(2)')]"));
        Assert.assertEquals(expectedQty, actualQty.substring(1, 2));

        //2.14 Verify the Total $698.00
        String expectedTotal = "$698.00";
        String actualTotal = getTextFromElement(By.xpath("(//strong[text()='$698.00'])[2]"));
        Assert.assertEquals(expectedTotal, actualTotal);
        System.out.println("Total : " + actualTotal);

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.16 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.17 Verify the Text “Welcome, Please Sign In!”
        String expectedWelcome = "Welcome, Please Sign In!";
        String actualWelcome = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        Assert.assertEquals(expectedWelcome, actualWelcome);

        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));

        //2.19 Verify the text “Register”
        String expectedRegister = "Register";
        String actualRegister = getTextFromElement(By.xpath("//h1[text()='Register']"));
        Assert.assertEquals(expectedRegister, actualRegister);

        //2.20 Fill the mandatory fields
        clickOnElement(By.xpath("//input[@id='gender-male']"));
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "MyFirstName");
        sendTextToElement(By.xpath("//input[@id='LastName']"), "MyLastName");
        selectByValueFromDropDown(By.name("DateOfBirthDay"), "9");
        selectByValueFromDropDown(By.name("DateOfBirthMonth"), "1");
        selectByValueFromDropDown(By.name("DateOfBirthYear"), "2000");
        Random randomEmail = new Random();
        int emailId = randomEmail.nextInt(9999);
        sendTextToElement(By.xpath("//input[@id='Email']"), "AB" + randomEmail + "gmail.com");
        sendTextToElement(By.xpath("//input[@id='Password']"), "Test1234");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "Test1234");

        //2.21 Click on “REGISTER” Button
        clickOnElement(By.xpath("//button[@id='register-button']"));

        //2.22 Verify the message “Your registration completed”
        String expectedRegisterSucc = "Your registration completed";
        String actualRegisterSucc = getTextFromElement(By.xpath("//div[contains(text(),'Your registration completed')]"));
        Assert.assertEquals(expectedRegisterSucc, actualRegisterSucc);

        //2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //2.24 Verify the text “Shopping cart”
        String expectedShoCart = "Shopping cart";
        String actualShoCart = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals(expectedShoCart, actualShoCart);

        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));


        //2.26 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.27 Fill the Mandatory fields
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "Harrow");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "5000 Station Road");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "HA8 9GG");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "02086252245");

        //2.28 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 new-address-next-step-button' and @name='save']"));

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.xpath("//label[text()='2nd Day Air ($0.00)']"));

        //2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.31 Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//label[text()='Credit Card']"));
        //Click on Continue
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //2.32 Select “Visa” From Select credit card dropdown
        // **** Visa type is by default selected.

        //2.33 Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Mr Hanuman");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4716994571504969");
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "08");
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2025");
        sendTextToElement(By.id("CardCode"), "667");

        //2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.35 Verify “Payment Method” is “Credit Card”
        String expectedPaymentMethod = "Credit Card";
        String actualPaymentMethod = getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]"));
        Assert.assertEquals(expectedPaymentMethod, actualPaymentMethod);
        System.out.println("Payment Method : " + actualPaymentMethod);

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        String expectedShippingMethod = "2nd Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//span[contains(text(), '2nd Day Air')]"));
        Assert.assertEquals(expectedShippingMethod, actualShippingMethod);
        System.out.println("Shipping Method : " + actualShippingMethod);

        //2.37 Verify Total is “$698.00”
        String expectedFinalValue = "$698.00";
        String actualFinalValue = getTextFromElement(By.xpath("(//strong[text()='$698.00'])[2]"));
        Assert.assertEquals("Final Value is: ", expectedFinalValue, actualFinalValue);

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));

        //2.39 Verify the Text “Thank You”
        String expectedThankYou = "Thank you";
        String actualThankYou = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        Assert.assertEquals(expectedThankYou, actualThankYou);
        System.out.println("Thanks You Message: " + actualThankYou);

        //2.40 Verify the message “Your order has been successfully processed!”
        String expectedSuccessMessage = "Your order has been successfully processed!";
        String actualSuccessMessage = getTextFromElement(By.xpath("// strong[text()='Your order has been successfully processed!']"));
        Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage);
        System.out.println("Thanks You Message: " + actualSuccessMessage);

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        //2.42 Verify the text “Welcome to our store”
        String expectedWelcome1 = "Welcome to our store";
        String actualWelcome1 = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        Assert.assertEquals(expectedWelcome1, actualWelcome1);
        System.out.println("Welcome to our store: " + actualWelcome);

        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[@class='ico-logout']"));

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String expectdUrl = "https://demo.nopcommerce.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectdUrl, actualUrl);
        System.out.println("Url: " + actualUrl);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}

