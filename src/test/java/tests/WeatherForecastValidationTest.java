package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.HomePage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class WeatherForecastValidationTest extends BaseTest{
    HomePage homePage = new HomePage(driver);
    private static int currentTemperature;
    private static int highestTemperature;
    private static int lowestTemperature;
    private static List<String> timeSlotsDisplayed;

    @Test
    public void forecastTimelineValidation () {
        homePage.goToHomePage();
        homePage.setUserLocation("10001");
        timeSlotsDisplayed = homePage.getTimeSlots();
        //Time in hours AM/PM
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ha");

        //Date calculation
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        //Expected time values
        ArrayList timeSlotsExpected = new ArrayList();
        timeSlotsExpected.add("NOW");

        //Add 2 hours to calculate expected time slots
        int increment = 2;
        int count = 0;
        do {
            cal.add(Calendar.HOUR_OF_DAY, increment);
            count = count + 1;
            timeSlotsExpected.add(simpleDateFormat.format(cal.getTime()));

        } while (count < 11);

        //Assert both time slot arrays match
        try {
            Assert.assertEquals(timeSlotsExpected,timeSlotsDisplayed);
        } catch (AssertionError e) {
            System.out.println("Timeline time slots are not incremental in intervals of 2 hours");
            throw e;
        }
        System.out.println("Timeline time slots are incremental in intervals of 2 hours");
    }

    @Test
    public void currentTemperatureRangeTest () {
        homePage.goToHomePage();
        homePage.setUserLocation("10001");
        currentTemperature = homePage.getCurrentTemperature();
        highestTemperature = homePage.getHighestTemperatureInTimeline();
        lowestTemperature = homePage.getLowestTemperatureInTimeLine();
        if (homePage.isCurrentTemperatureInValidRange(currentTemperature,highestTemperature,lowestTemperature)) {
            System.out.println("Current temperature of " + currentTemperature + " degrees is in a valid range");
        }
        else {
            System.out.println("Current temperature of " + currentTemperature + " degrees is not in a valid range");
        }
    }

}
