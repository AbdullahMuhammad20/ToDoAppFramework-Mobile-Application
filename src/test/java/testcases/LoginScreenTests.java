package testcases;

import base.Base;
import org.testng.annotations.Test;
import screens.LoginScreen;

public class LoginScreenTests extends Base
{
    LoginScreen loginScreen ;




    @Test(priority = 1)
    public void checkLogin()
    {
        loginScreen = new LoginScreen();
        loginScreen.FillEmailAndPassword("Abd3@gmail.com","Test123");
    }



    @Test(priority = 1)
    public void checkSignUP()
    {
        loginScreen = new LoginScreen();
        loginScreen.SignUp2("Abdullahs","Abd3@gmail.com","Test123");
        loginScreen.LogoutFunction();
    }
    @Test(priority = 3)
    public void addNewTask()
    {
        loginScreen = new LoginScreen();
        loginScreen.AddNewTasks("First Task");
    }

    @Test(priority = 4)
    public void logOut()
    {
        loginScreen = new LoginScreen();
        loginScreen.LogoutFunction();
    }

}
