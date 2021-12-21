package com.learnautomation.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public static void captureScreenshot(WebDriver driver) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(src, new File("./Screenshots/CMR_" + getCurrentDateTime() + ".png"));
            System.out.println("Screenshot Captured");
        } catch (IOException e) {
            System.out.println("Unable to capture Screenshot" + e.getMessage());
        }
    }

    public static String getCurrentDateTime() {
        DateFormat customDateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return customDateFormat.format(currentDate);
    }

    public static String getScreenShot(WebDriver driver, String screenshotName) throws Exception {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+getCurrentDateTime()+".png";
        File finalDestination = new File(destination);
        FileHandler.copy(source, finalDestination);
        //FileUtils.copyFile(source, finalDestination);
        return destination;
    }


}
