package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    protected static WebDriver driver;
    private static String FILE_PATH = "/Users/ddarne/chromedriver";

    @BeforeClass
    public static void setup () {
        //Create a Chrome driver. All test classes use this.
        System.setProperty("webdriver.chrome.driver", FILE_PATH);
        driver = new ChromeDriver();

        //Maximize Window
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void teardown () {
        driver.quit();
    }
}
