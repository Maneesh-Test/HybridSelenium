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

public class BasePage {

    public static WebDriver driver;
    public static ConfigDataProvider config;
    public ExtentReports reports;
    public static ExtentTest logger;

    @BeforeSuite
    public void setUpSuite() {
        config = new ConfigDataProvider();
        String platForm = "Platform";
        String platFormValue = "CRM QA Testing";
        String paramEnv = "Environment";
        String envValue = "QA";
        String paramUsrName = "User Name";
        String usrNameValue = "Maneesh Chandra R";
        String filePath = "";
        filePath = System.getProperty("user.dir") + "/Reports/login.html"; //Mac
        reports = new ExtentReports(filePath);
        reports.addSystemInfo(platForm, platFormValue)
                .addSystemInfo(paramEnv, envValue)
                .addSystemInfo(paramUsrName, usrNameValue);
        reports.loadConfig(new File(System.getProperty("user.dir") + "/XMLfiles/extentReport.xml"));
        //config = new ConfigDataProvider();
    }

    @BeforeClass
    public void setUp() {
        driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());

    }

    @AfterClass
    public void tearDown() {
        BrowserFactory.quitBrowser(driver);
    }

    /**
     * <h3>This method is used to capture screenshot and get result using ITestResult interface</h3>
     * <i>When any of the test cases are failed or skipped screenshot is attached in the extent report</i>
     *
     * @param result
     * @throws Exception
     * @author Maneesh Chandra R
     */
    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
            String screenshotPath = Helper.getScreenShot(driver, result.getName());
            //To add it in the extent report
            logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        }
        // ending test
        //endTest(logger) : It ends the current test and prepares to create HTML report
        reports.endTest(logger);
        reports.flush();
    }
}
