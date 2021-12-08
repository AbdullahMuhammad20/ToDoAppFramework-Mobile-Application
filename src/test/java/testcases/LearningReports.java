package testcases;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LearningReports {
    public ExtentReports extent;
    public ExtentTest logger;

    @BeforeSuite
    public void beforeSuite() {
        extent = new ExtentReports("Reports/index.html");
        extent.addSystemInfo("Framework Type", "Appium Page Object");
        extent.addSystemInfo("Author", "Abdullah");
        extent.addSystemInfo("Environment", "Windows");
        extent.addSystemInfo("App", "ToDo App");

    }

    @AfterSuite
    public void afterSuite()
    {

        extent.flush();
    }

    @BeforeMethod
    public void beforeMethod(Method method)
    {
        logger = extent.startTest(method.getName());
    }
    @Test
    public void test1()
    {
        System.out.println("Test Pass");
    }
    @Test
    public void test2()
    {
        System.out.println("Test Pass2");
        Assert.fail("Test Reporting");
    }


    @AfterMethod
    public void afterMethod(Method method, ITestResult testResult)
    {
        if (testResult.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(LogStatus.PASS,"Test is Passed");
        }
        else if (testResult.getStatus() == ITestResult.FAILURE)
        {
            logger.log(LogStatus.FAIL,"Test is Fail");
            logger.log(LogStatus.FAIL,testResult.getThrowable());
        }
        else
        {
            logger.log(LogStatus.SKIP,"Test is Skipped");
        }
    }
}
