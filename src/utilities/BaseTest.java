package utilities;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;

    public void openBrowser(String baseUrl) {

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe"); // give key and copy value from drivers file.
        driver = new ChromeDriver(); // Creating an object
        // Launching the URL
        driver.get(baseUrl);
        // Maximise Window
        driver.manage().window().maximize();
        //we give implicit time to driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
