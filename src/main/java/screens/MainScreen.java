package screens;

import base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainScreen extends Base
{
    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//*[@text='Logout']")
    private MobileElement logoutButton;

    //@iOSXCUITFindBy(xpath = "")
    @AndroidFindBy(xpath = "//*[@text='Add new Task']")
    private MobileElement newTaskButton;


    public void LogoutFunction()
    {
        if(newTaskButton.isDisplayed() && logoutButton.isDisplayed())
        {
            logoutButton.click();
        }
        else
        {

        }

    }
}
