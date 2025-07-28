package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.time.Duration;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verify_account_registration(){
        logger.info("*****Starting TC001_AccountRegistrationTest *****");
        try {
            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("Clicked on MyAccount Link");

            hp.clickRegister();
            logger.info("Clicked on Register Link");

            AccountRegistrationPage registerPage = new AccountRegistrationPage(driver);
            logger.info("Providing customer details...");
            registerPage.setFirstName(randomString().toUpperCase());
            registerPage.setLastName(randomString().toUpperCase());
            registerPage.setEmail(randomString() + "@gmail.com");
            registerPage.setTelephone(randomNumbers());

            String password = randomAlphaNumeric();
            registerPage.setPassword(password);
            registerPage.setConfirmPassword(password);

            registerPage.setPrivacyPolicy();
            registerPage.clickContinue();

            logger.info("Validating expected message...");
            // Step 3: Add Explicit Wait HERE
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            String confMsg = wait.until(ExpectedConditions.visibilityOf(
                    registerPage.getConfirmationMsg() // Waits for element to be visible
            )).getText(); // Then extracts text
            logger.info("Actual confirmation message: " + confMsg); // Log for debugging
            Assert.assertEquals(confMsg, "Your Account Has Been Created!",
                    "Confirmation message mismatch");
        }catch (Exception e) {
            logger.error("Test Failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC001_AccountRegistrationTest *****");
    }
}
