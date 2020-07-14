package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage (WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,15);
    }

    //Wait Wrapper Method
    public void waitForVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    //Method to click an element
    public void click (By elementBy) {
        waitForVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    //Method to write into a form element
    public void enterText (By elementBy, String text) {
        waitForVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    //Method to read from a form element and return the text
    public String readText (By elementBy) {
        waitForVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

}
