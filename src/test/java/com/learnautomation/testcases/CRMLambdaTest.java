package com.learnautomation.testcases;

import com.learnautomation.Pages.BasePage;
import com.learnautomation.Pages.DashBoardPage;
import com.learnautomation.Pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class CRMLambdaTest extends BasePage {

    private LoginPage loginPage;
    private DashBoardPage dashBoardPage;

    @BeforeMethod(alwaysRun = true)
    public void setupSuite(Method method) {
        loginPage = new LoginPage(driver);
        dashBoardPage = new DashBoardPage(driver);
        logger = reports.startTest("Automation Test on CRM Lambda Webpage");
    }

    @Test(priority = 0)
    public void handleStartUp() {
        loginPage.navigateToDashboardPage();
        dashBoardPage.navigateToDashboardPage();
    }
}
