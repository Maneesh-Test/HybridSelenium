package com.learnautomation.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {
        if (browserName.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver");
            driver = new ChromeDriver();

        } else if (browserName.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver");
            driver = new FirefoxDriver();
        } else if (browserName.equals("Safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("Invalid Browser");
        }
        PageUtil.implicitlyWait();
        driver.manage().window().maximize();
        driver.get(appURL);
        PageUtil.implicitlyWait();
        return driver;
    }

    public static void quitBrowser(WebDriver driver) {
        driver.quit();

    }

}
