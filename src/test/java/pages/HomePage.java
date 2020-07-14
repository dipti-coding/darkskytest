package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage (WebDriver driver) {
        super(driver);
    }

    //Home page URL
    private static String HOME_PAGE_URL = "https://darksky.net/";

    private static String zipcode = "10001";

    //Element Locators
    By searchLocationBox = By.cssSelector("#searchForm > input:nth-child(2)");
    By currentTemperature = By.cssSelector(".summary.swap");
    By allTemperaturesOnTimeline = By.cssSelector(".temps span:last-child");
    By allTimeSlotsOnTimeLine = By.xpath("//*[@id=\"timeline\"]/div/div[3]");

    //Navigate to Dark sky home page
    public HomePage goToHomePage (){
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public void setUserLocation(String zipcode) {
        driver.findElement(searchLocationBox).clear();
        driver.findElement(searchLocationBox).sendKeys(zipcode);
    }



    public int getCurrentTemperature() {
        String current = driver.findElement(currentTemperature).getText();
        int temp = parseTemp(current);
        return temp;
    }

    public int getHighestTemperatureInTimeline() {
        int temp = getCurrentTemperature();
        List<WebElement> allTemperatures = driver.findElements(allTemperaturesOnTimeline);
        int highestTemperature = temp;
        for (WebElement tempInTime: allTemperatures) {
            String strLineTemp = tempInTime.getText();
            int lineTemp = parseTemp(strLineTemp);
            if (lineTemp > highestTemperature){
                highestTemperature  = lineTemp;
            }

        }
        return highestTemperature;
    }

    public int getLowestTemperatureInTimeLine() {
        int temp = getCurrentTemperature();
        List<WebElement> allTemperatures = driver.findElements(allTemperaturesOnTimeline);
        int lowestTemperature = temp;
        for (WebElement tempInTime: allTemperatures) {
            String strLineTemp = tempInTime.getText();
            int lineTemp = parseTemp(strLineTemp);
            if (lineTemp < lowestTemperature ){
                lowestTemperature = lineTemp;
            }

        }
        return lowestTemperature;
    }

    public boolean isCurrentTemperatureInValidRange(Integer current, Integer highest, Integer lowest) {
        if (current < highest && current > lowest) {
            return true;
        }
        return false;
    }

    public List<String> getTimeSlots () {
        WebElement timeSlotsOnTimeLine = driver.findElement(allTimeSlotsOnTimeLine);
        List<String> timeNow = Arrays.asList(timeSlotsOnTimeLine.getText().toUpperCase().split("\\s+"));
        return timeNow;
    }

    private int parseTemp(String strTemp) {
        return Integer.parseInt(strTemp.substring(0, 2));
    }


}
