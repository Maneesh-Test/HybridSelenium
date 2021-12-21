package com.learnautomation.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       // PageUtil.implicitlyWait();
        driver.manage().window().maximize();
        driver.get(appURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //PageUtil.implicitlyWait();
        return driver;
    }

    public static void quitBrowser(WebDriver driver) {
        driver.quit();

    }

}
