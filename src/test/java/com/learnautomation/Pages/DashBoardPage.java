package com.learnautomation.Pages;

import com.learnautomation.utility.Helper;
import com.learnautomation.utility.PageUtil;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITest;

public class DashBoardPage extends BasePage {
    protected WebDriver driver;

    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "header__aside__menu")
    WebElement dashBoardMenu;

    @FindBy(how = How.XPATH, using = "//img[@class='aside__menu__icon' and @alt='Dashboard']")
    WebElement dashboardText;


    public void verifyDashBoardText(String actual, String expected) {
        PageUtil.assertEquals(actual, expected);
    }

    public void navigateToDashboardPage() {
        logger.log(LogStatus.INFO, "In Dashboard Page");
        String attributeText = PageUtil.getAttribute(dashboardText,"Alt");
        verifyDashBoardText(attributeText,config.getDataFromConfig("dashBoardText"));
    }
}
