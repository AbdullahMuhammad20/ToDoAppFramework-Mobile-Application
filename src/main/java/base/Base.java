package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base
{
    protected static AndroidDriver<AndroidElement> driver ;
    protected  FileInputStream inputStream;
    protected Properties prop;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public static File snapshot;

    public Base(){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger = extent.startTest(method.getName());
    }


    @Parameters({"deviceName","platformName","platformversion"})
    @BeforeClass
    public void beforeClass(String deviceName,String platformName, String platformVersion) throws IOException {

            File propFile = new File("src/main/resources/config/config.properties");

            inputStream = new FileInputStream(propFile);
            prop = new Properties();
            prop.load(inputStream);

            if (platformName.equalsIgnoreCase("Android")) {

                File androidApp = new File(prop.getProperty("androidAppPath"));
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                caps.setCapability(MobileCapabilityType.VERSION, platformVersion);
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.get("AndroidAutomationName"));
                caps.setCapability(MobileCapabilityType.APP, androidApp.getAbsolutePath());

                driver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("AppiumServerLink")), caps);
            }
            else if (platformName.equalsIgnoreCase("IOS"))
            {
                /* IOS SetUp
                File IOSApp = new File(prop.getProperty("IOSAppPath"));
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                caps.setCapability(MobileCapabilityType.VERSION, platformVersion);
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.get("IOSAutomationName"));
                caps.setCapability(MobileCapabilityType.APP, IOSApp.getAbsolutePath());

                driver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("AppiumServerLink")), caps);

                 */
            }

            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass()
    {
       driver.quit();
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult testResult)  {

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        File Image = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(Image,new File("Screenshots/"+ method.getName() +".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ImageFullPath = System.getProperty("user.dir") + File.separator + "Screenshots/"+method.getName() +".jpg";
        System.out.println(ImageFullPath);

        if (testResult.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(LogStatus.PASS,"Test is Passed");
            logger.log(LogStatus.PASS,logger.addScreenCapture(ImageFullPath));

        }
        else if (testResult.getStatus() == ITestResult.FAILURE)
        {
            logger.log(LogStatus.FAIL,"Test is Fail");
            logger.log(LogStatus.FAIL,testResult.getThrowable());
            logger.log(LogStatus.FAIL,logger.addScreenCapture(ImageFullPath));
        }
        else
        {
            logger.log(LogStatus.SKIP,"Test is Skipped");
            logger.addScreenCapture(ImageFullPath);
        }



    }


    // Explicit Wait
    public void waitForElementVisible(MobileElement element)
    {
        WebDriverWait wait =  new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementInteraction(MobileElement element)
    {
        WebDriverWait wait =  new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
