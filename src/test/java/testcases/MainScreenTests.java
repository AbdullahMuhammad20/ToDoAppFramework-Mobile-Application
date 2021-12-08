package testcases;

import base.Base;
import org.testng.annotations.Test;
import screens.MainScreen;
import screens.LoginScreen;

public class MainScreenTests extends Base
{
    LoginScreen loginScreen;
    MainScreen mainScreen;


    @Test
    public void checkLogin()
    {
        loginScreen = new LoginScreen();
        loginScreen.FillEmailAndPassword("Abdmohamed033@gmail.com","Test123");
    }

    @Test
    public void logOut()
    {
        mainScreen.LogoutFunction();
    }
}
