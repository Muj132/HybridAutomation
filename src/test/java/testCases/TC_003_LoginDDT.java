package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC_003_LoginDDT extends BaseClass
{

    @Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups = "DataDriven")
    public void verify_loginDDT(String email, String password, String expected)
    {
        logger.info("**** Starting TC_003_LoginDDT *****");

        try {

            //Home page
            HomePage hp=new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin(); //Login link under MyAccount

            //Login page
            LoginPage lp=new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(password);
            lp.clickLogin(); //Login button

            //My Account Page
            MyAccountPage macc=new MyAccountPage(driver);
            boolean targetPage=macc.isAccountPageExists();

            if(expected.equalsIgnoreCase("Valid"))
            {
                if(targetPage)
                {
                    macc.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.fail();
                }
            }

            if(expected.equalsIgnoreCase("Invalid")) // if data is invalid
            {
                if(targetPage) // and you're able to log in then logout and fail since you shouldn't be able to log in with invalid credentials
                {
                    macc.clickLogout();
                    Assert.fail();
                }
                else
                {
                    Assert.assertTrue(true); // if credentials are invalid, and you can't log in then the test works
                }
            }
        }
        catch(Exception e)
        {
            Assert.fail("An exception occurred: " + e.getMessage());
        }

        logger.info("**** Finished TC_003_LoginDDT *****");
    }

}








