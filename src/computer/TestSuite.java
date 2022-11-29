package computer;
// **** Created By Harshit Patel ****

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.Random;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        Actions actions = new Actions(driver);

        //1.1 Click on Computer Menu.
        WebElement computer = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        //1.2 Click on Desktop
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();
        //1.3 Select Sort By position "Name: Z to A"
        selectByValueFromDropDown(By.id("products-orderby"), "6");
        //1.4 Verify the Product will arrange in Descending order.
        String expectedTextZToA = "Name: Z to A";
        String actualTextZToA = getTextFromElement(By.xpath("//select[@id='products-orderby']/option[@value='6']"));
        Assert.assertEquals("Product is not in order Z to A", expectedTextZToA, actualTextZToA);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        Actions actions = new Actions(driver);
        // mouse hover computer
        WebElement computer = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        //mouse hover desktop
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        //2.2 Click on Computer and Desktop
        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();
        // 2.3 Select Sort By position "Name: A to Z"
        selectByValueFromDropDown(By.id("products-orderby"), "5");
        Thread.sleep(6000);
        // 2.4 Click on "Add To Cart"
        WebElement cart = driver.findElement(By.xpath("(//button[contains(text(),'to cart')])[1]"));
        actions.moveToElement(cart).click().build().perform();

        //2.5 Verify the Text "Build your own computer"
        String expectedTextInPage = "Build your own computer";
        String actualTextInPage = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        Assert.assertEquals(actualTextInPage, expectedTextInPage);

        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.name("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7.Select "8GB [+$60.00]" using Select class.
        selectByVisibleTextFromDropDown(By.name("product_attribute_2"), "8GB [+$60.00]");

        //2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//label[@for='product_attribute_3_7']"));

        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//label[@for='product_attribute_4_9']"));
        // to give extra wait
        Thread.sleep(6000);

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        clickOnElement(By.xpath("//label[@for='product_attribute_5_12']"));

        // 2.11 Verify the price "$1,475.00"
        String expectedPrice = "$1,475.00";
        String actualPrice = getTextFromElement(By.xpath("//span[@id='price-value-1']"));
        Assert.assertEquals(actualTextInPage, expectedTextInPage);

        //2.12 Click on "ADD TO CARD" Button.
        WebElement cart1 = driver.findElement(By.xpath("(//button[contains(text(),'to cart')])[1]"));
        actions.moveToElement(cart1).click().build().perform();

        //2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        System.out.println("Actual Message : " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);

        //After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class='close']"));

        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        //2.15 Verify the message "Shopping cart"
        String expectedShoppingCartMessage = "Shopping cart";
        String actualShoppingCartMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals(expectedShoppingCartMessage, actualShoppingCartMessage);
        System.out.println("Nevigated to Shopping cart Message: " + actualShoppingCartMessage);


        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        //2.17 Verify the Total"$2,950.00"
        String expectedTotalPrice = "$2,950.00";
        String actualTotalPrice = getTextFromElement(By.xpath("//tbody/tr[1]/td[6]/span[1]"));
        Assert.assertEquals(expectedTotalPrice, actualTotalPrice);
        System.out.println("Updated Price : " + actualTotalPrice);

        //2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.19 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.20 Verify the Text “Welcome, Please Sign In!”
        String expectedWelcomeMessage = "Welcome, Please Sign In!";
        String actualWelcomeMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        Assert.assertEquals(expectedWelcomeMessage, actualWelcomeMessage);
        System.out.println("Welcome Message : " + actualWelcomeMessage);

        //2.21Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));

        //2.22 Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "MyFirstName");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "MyLastName");
        Random randomEmail = new Random();
        int emailId = randomEmail.nextInt(9999);
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), randomEmail + ".com");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Company']"), "MyCompany");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "Harrow");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "5000 Village Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "HA8 9FY");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "02089055566");

        //2.23 Click on “CONTINUE”
        clickOnElement(By.name("save"));

        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));

        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.26 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //2.27 Select “Master card” From Select credit card dropdown
        //2.28 Fill all the details

        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Mr Ramandip");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5543540144505151");
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "05");
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2023");
        sendTextToElement(By.id("CardCode"), "871");

        //2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@type='button' and @onclick='PaymentInfo.save()']"));

        //2.30 Verify “Payment Method” is “Credit Card”
        String expectedPaymentMethod = "Credit Card";
        String actualPaymentMethod = getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]"));
        Assert.assertEquals(expectedPaymentMethod, actualPaymentMethod);
        System.out.println("Payment Method : " + actualPaymentMethod);

        //2.32 Verify “Shipping Method” is “Next Day Air”
        String expectedShippingMethod = "Next Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//span[contains(text(), 'Next Day Air')]"));
        Assert.assertEquals(expectedShippingMethod, actualShippingMethod);
        System.out.println("Shipping Method : " + actualShippingMethod);

        //2.33 Verify Total is “$2,950.00”
        String expectedFinalValue = "$2,950.00";
        String actualFinalValue = getTextFromElement(By.xpath("(//strong[text()='$2,950.00'])[2]"));
        Assert.assertEquals("Final Value is: ", expectedFinalValue, actualFinalValue);

        // 2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));


        //2.35 Verify the Text “Thank You”
        String expectedThankYou = "Thank you";
        String actualThankYou = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        Assert.assertEquals(expectedThankYou, actualThankYou);
        System.out.println("Thanks You Message: " + actualThankYou);


        //2.36 Verify the message “Your order has been successfully processed!”
        String expectedSuccessMessage = "Your order has been successfully processed!";
        String actualSuccessMessage = getTextFromElement(By.xpath("// strong[text()='Your order has been successfully processed!']"));
        Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage);
        System.out.println("Thanks You Message: " + actualSuccessMessage);

        //2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        //2.37 Verify the text “Welcome to our store”
        String expectedWelcome = "Welcome to our store";
        String actualWelcome = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        Assert.assertEquals(expectedWelcome, actualWelcome);
        System.out.println("Welcome to our store: " + actualWelcome);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}




