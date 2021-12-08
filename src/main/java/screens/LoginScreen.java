package screens;
import base.Base;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.testng.Assert;

import javax.lang.model.element.ModuleElement;

public class LoginScreen extends Base {
    // the blow line of code to know how to access the elements on IOS
    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.widget.EditText[@index='1']")
    private MobileElement emailField;

    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//android.widget.EditText[@index='2']")
    private MobileElement passwordField;

    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//*[@text='Login']")
    private MobileElement loginButton;

    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//*[@text='Signup']")
    private MobileElement signUPButton;


    //////////////// Main Screen
    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//*[@text='Logout']")
    private MobileElement logoutButton;

    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//*[@text='Add new Task']")
    private MobileElement mainNewTaskButton;
    /////////////////////////////////

    /////////////// Tasks Screen
    //@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='NewTask']")
    @AndroidFindBy(xpath = "//*[@text='Enter a new Task']")
    private MobileElement taskNameField;

    @AndroidFindBy(xpath = "//*[@text='Add new Task']")
    private MobileElement addTaskButton;

    @AndroidFindBy(xpath = "//*[@text='Back']")
    private MobileElement backButton;
    ////////////////////////////

    /////////////// Sign Up Screen
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter your Full name']")
    private MobileElement sFullField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter your Email']")
    private MobileElement sEmailField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter your Password']")
    private MobileElement sPassField;

    @AndroidFindBy(xpath = "//*[@text='Signup']")
    private MobileElement sSignUPButton;

    //////////////////////////////

    public void SignUp2(String fullName,String signMail,String signPass)
    {

        waitForElementVisible(signUPButton);
        waitForElementInteraction(signUPButton);
        signUPButton.click();

        MobileElement FullName;
        FullName = driver.findElement(MobileBy.xpath("//android.widget.EditText[@index='0']"));
        waitForElementVisible(FullName);
        waitForElementInteraction(FullName);
        FullName.sendKeys(fullName);

        MobileElement SignEmail;
        SignEmail = driver.findElementsByXPath("//android.widget.EditText[@text='Enter your Email']").get(1);
        waitForElementVisible(SignEmail);
        waitForElementInteraction(SignEmail);
        SignEmail.sendKeys(signMail);

        MobileElement SignPass;
        SignPass = driver.findElementsByXPath("//android.widget.EditText[@text='Enter your Password']").get(1);
        waitForElementVisible(SignPass);
        waitForElementInteraction(SignPass);
        SignPass.sendKeys(signPass);



        sSignUPButton.click();

    }


    public void SignUP(String fullName,String signMail,String signPass)
    {
        waitForElementVisible(signUPButton);
        waitForElementInteraction(signUPButton);
        signUPButton.click();

        waitForElementVisible(sFullField);
        waitForElementInteraction(sFullField);
        sFullField.sendKeys(fullName);

        waitForElementVisible(sEmailField);
        waitForElementInteraction(sEmailField);
        sEmailField.sendKeys(signMail);

        waitForElementVisible(sPassField);
        waitForElementInteraction(sPassField);
        sPassField.sendKeys(signPass);

        waitForElementVisible(sSignUPButton);
        waitForElementInteraction(sSignUPButton);
        sSignUPButton.click();

    }


    public void FillEmailAndPassword(String email, String password)
    {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();

        waitForElementVisible(mainNewTaskButton);
        waitForElementInteraction(mainNewTaskButton);

        mainNewTaskButton.click();
    }

    public void LogoutFunction()
    {
        if(mainNewTaskButton.isDisplayed() && logoutButton.isDisplayed())
        {
            logoutButton.click();
        }
        else
        {

        }

    }

    public void AddNewTasks(String taskName)
    {
      waitForElementVisible(taskNameField);
      waitForElementInteraction(taskNameField);
      taskNameField.sendKeys(taskName);
      waitForElementVisible(addTaskButton);
      waitForElementInteraction(addTaskButton);
      addTaskButton.click();

    }

    public void BackScreen()
    {
        backButton.click();
    }
}
