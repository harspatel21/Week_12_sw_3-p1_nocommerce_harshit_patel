package homepage;
// **** Created By Harshit Patel ****

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class TopMenuTest extends Utility {


    String baseUrl = "https://demo.nopcommerce.com/";
    static String menu = "Books";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        Actions actions = new Actions(driver);
        WebElement clickOnMenu = driver.findElement(By.linkText(menu));
        actions.moveToElement(clickOnMenu).click().build().perform();
    }

    @Test
    public void verifyPageNavigation() {
        selectMenu(menu);

        String expectedTextInPage = menu;
        String actualTextInPage = getTextFromElement(By.xpath("//h1[contains(text()," + "'" + menu + "')]"));
        Assert.assertEquals(actualTextInPage, expectedTextInPage);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}


