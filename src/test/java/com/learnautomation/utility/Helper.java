package com.learnautomation.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Helper {

    /**<p>public static void captureScreenshot(WebDriver driver) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(src, new File("./Screenshots/CMR_" + getCurrentDateTime() + ".png"));
            System.out.println("Screenshot Captured");
        } catch (IOException e) {
            System.out.println("Unable to capture Screenshot" + e.getMessage());
        }</p>
    }*/


    /**
     * Time format method is a particular format
     */

    public static String getCurrentDateTime() {
        DateFormat customDateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return customDateFormat.format(currentDate);
    }

    /**
     * <h3>This method is used to get a screenshot for the failed test case</h3>
     * @param driver
     * @param screenshotName
     */
    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + getCurrentDateTime() + ".png";
        File finalDestination = new File(destination);
        FileHandler.copy(source, finalDestination);
        //FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    /**
     * The implicitlyWait method contains public interface
     * Timeouts default WebDriver.Timeouts implicitlyWait method
     * @param driver
     */
    public static void implicitlyWait(WebDriver driver){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }catch (Exception e){
            System.out.println("Implicitly wait is not working"+e.getMessage());
        }
    }

    /**
     * The windowMaximize method consists of window maximize method from WebDriver class
     * @param driver
     */
    public static void windowMaximize(WebDriver driver){
        driver.manage().window().maximize();
    }

}
