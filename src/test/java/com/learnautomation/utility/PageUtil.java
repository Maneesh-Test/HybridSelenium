package com.learnautomation.utility;

import com.learnautomation.Pages.BasePage;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PageUtil extends BasePage {

    public static WebDriver driver;

    public static final int SMALL_PAUSE = 3;
    public static final int MEDIUM_PAUSE = 5;
    public static final int LARGE_PAUSE = 10;
    public static final int XLARGE_PAUSE = 60;
    public static final int MAX_WAIT_TIME = 180;
    public static final int POLLING_TIME = 500;

    /**
     * <h3>This method is used for the visibility of WebElement in the DOM</h3>
     *
     * @param webElement
     * @param timeOut
     * @return status
     * @author Maneesh Chandra R
     */
    public static boolean waitForVisible(WebElement webElement, long timeOut) {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    /**
     * <p>This method is used to <i>Display WebElement</i> </p>
     *
     * @param webElement
     * @return status true/false
     * @author Maneesh Chandra R
     */
    public static boolean isPresent(WebElement webElement) {
        boolean status = false;
        try {
            status = webElement.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean waitForInVisible(WebElement webElement, int timeOut) {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.invisibilityOf(webElement));
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean waitForPresenceOfElement(WebElement webElement) {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MEDIUM_PAUSE));
            wait.until(ExpectedConditions.visibilityOf(webElement));
            status = webElement.isDisplayed();
        } catch (Exception e) {
            return status;
        }
        return status;
    }

    public static boolean containText(WebElement webElement, String expectedText) {
        String actualText = null;
        boolean status = false;
        try {
            actualText = webElement.getText();
            status = actualText.contains(expectedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean verifyText(WebElement webElement, String text) {
        boolean status = false;
        try {
            status = webElement.getText().equalsIgnoreCase(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean isEnabled(WebElement webElement) {
        boolean status = false;
        waitForVisible(webElement, MEDIUM_PAUSE);
        try {
            status = webElement.isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String getAttribute(WebElement webElement, String attribute) {
        String attributeValue = "";
        try {
            attributeValue = webElement.getAttribute(attribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attributeValue;

    }

    public boolean verifyAttribute(WebElement webElement, String attribute, String attributeValue) {
        boolean status = false;
        waitForVisible(webElement, MEDIUM_PAUSE);
        try {
            status = webElement.getAttribute(attribute).equalsIgnoreCase(attributeValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean sendKeys(WebElement webElement, String text) {
        boolean status = false;
        try {
            webElement.clear();
            webElement.sendKeys(text);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean click(WebElement webElement) {
        boolean status = false;
        try {
            webElement.click();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void hardAssertion(boolean status, String message) {
        if (status) {
            logger.log(LogStatus.PASS, message);

        } else {
            logger.log(LogStatus.FAIL, message);
            Assert.fail();
        }
    }

    /**
     * @param actual
     * @param expected
     */
    public static void assertEquals(String actual, String expected) {
        try {
            if (actual.equals(expected)) {
                logger.log(LogStatus.PASS, "Actual Text: " + actual + " is equal to Expected Text: " + expected);
            } else {
                logger.log(LogStatus.FAIL, "Actual Text: " + actual + " is not equal to Expected Text: " + expected);
                Assert.assertEquals(actual, expected);
            }
        } catch (Exception e) {
            System.out.println("Assertion Failed: " + e.getMessage());
        }
    }

    public static void navigationMethods(String choice) {
        switch (choice) {
            case "Back":
                driver.navigate().back();
                break;
            case "Forward":
                driver.navigate().forward();
                break;
        }
    }

    /**
     * Deprecated
     */
    @Deprecated
    public static void implicitlyWait() {
        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readXMLValues(String filePath, String root, String tagName) {
        String value = null;
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //an instance of builder to parse the specified xml file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(root);
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element eElement = (Element) node;
                    value = eElement.getElementsByTagName(tagName).item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static By locatorValue(WebElement webElement) {
        String webElementValue = webElement.toString();
        String locatorVal = null;
        By byVal = null;
        if (webElementValue.contains("accessibility:")) {
            locatorVal = webElement.toString().split("accessibility:")[1];
            byVal = By.id(locatorVal.trim().substring(0, locatorVal.length() - 3));
        } else if (webElementValue.contains("id:")) {
            locatorVal = webElement.toString().split("id:")[1];
            byVal = By.id(locatorVal.trim().substring(0, locatorVal.length() - 3));
        } else if (webElementValue.contains("xpath:")) {
            locatorVal = webElement.toString().split("xpath:")[1];
            byVal = By.xpath(locatorVal.trim().substring(0, locatorVal.length() - 3));
        }
        return byVal;
    }

    public static boolean waitForPresenceOfElement(WebElement webElement, int timeOut) {
        boolean found = false;
        for (int i = 1; i < timeOut; i++) {
            try {
                if (!isPresent(webElement)) {
                    Thread.sleep(1000);
                } else {
                    found = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Element not found: " + e.getMessage());
            }
        }
        return found;
    }

}
