package com.learnautomation.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class BrowserFactory {

    public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {
        switch (browserName) {
            case "Chrome" -> {
                System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver");
                driver = new ChromeDriver();
            }
            case "Firefox" -> {
                System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver");
                driver = new FirefoxDriver();
            }
            case "Safari" -> driver = new SafariDriver();
            default -> System.out.println("Invalid Browser");
        }
        Helper.implicitlyWait(driver);
        Helper.windowMaximize(driver);
        driver.get(appURL);
        return driver;
    }

    public static void quitBrowser(WebDriver driver) {
        driver.quit();

    }

}
