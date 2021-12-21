package com.learnautomation.Pages;

import com.learnautomation.utility.PageUtil;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage {

    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(how = How.NAME, using = "email")
    WebElement userName;

    @FindBy(how = How.NAME, using = "password")
    WebElement passWord;

    @FindBy(how = How.ID, using = "login-button")
    WebElement clickLoginButton;

    public void enterCredentials(String usrName, String psWord) {
        PageUtil.sendKeys(userName, usrName);
        PageUtil.sendKeys(passWord, psWord);
    }

    public void clickOnLoginButton() {
        PageUtil.click(clickLoginButton);
    }

    public void navigateToDashboardPage() {
        logger.log(LogStatus.INFO, "Started the Login Test");
        logger.log(LogStatus.INFO, "Enter User name and Password");
        enterCredentials(config.sendUsername(), config.sendPassword());
        logger.log(LogStatus.PASS,"Click on Login button");
        clickOnLoginButton();
        logger.log(LogStatus.PASS,"Login Successful");
    }

}
