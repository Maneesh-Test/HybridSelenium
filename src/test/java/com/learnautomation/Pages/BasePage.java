package com.learnautomation.Pages;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.Helper;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class BasePage {

    public static WebDriver driver;
    public static ConfigDataProvider config;
    public ExtentReports reports;
    public static ExtentTest logger;

    @BeforeSuite
    public void setUpSuite() {
        String filePath = "";
        filePath = System.getProperty("user.dir") + "/Reports/login.html"; //Mac
        reports = new ExtentReports(filePath);
        reports.addSystemInfo("Platform", "CRM QA Testing")
                .addSystemInfo("Environment", "QA")
                .addSystemInfo("User Name", "Maneesh Chandra Rajendran");
        reports.loadConfig(new File(System.getProperty("user.dir") + "/XMLfiles/extentReport.xml"));
        config = new ConfigDataProvider();
    }


    @BeforeClass
    public void setUp() {
        driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());

    }

    @AfterClass
    public void tearDown() {
        BrowserFactory.quitBrowser(driver);
    }

    /*@AfterMethod
    public void tearDownMethod(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            Helper.captureScreenshot(driver);
        }
        reports.flush();
    }*/

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
           // logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method.
            String screenshotPath = Helper.getScreenShot(driver, result.getName());
//To add it in the extent report
            logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
        }else if(result.getStatus() == ITestResult.SKIP){
            logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
        }
// ending test
//endTest(logger) : It ends the current test and prepares to create HTML report
        reports.endTest(logger);
        reports.flush();
    }
}
