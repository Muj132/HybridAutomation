package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
    WebDriver driver;

    public AccountRegistrationPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement txtFirstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement txtLastName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement txtTelephone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement txtPassword;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement txtConfirmPassword;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement chkPolicy;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;

    public void setFirstName(String firstName){
        txtFirstName.sendKeys(firstName);
    }
    public void setLastName(String LastName){
        txtLastName.sendKeys(LastName);
    }
    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void setTelephone(String tele){
        txtTelephone.sendKeys(tele);
    }
    public void setPassword(String pwd){
        txtPassword.sendKeys(pwd);
    }
    public void setConfirmPassword(String pwd){
        txtConfirmPassword.sendKeys(pwd);
    }
    public void setPrivacyPolicy(){
        chkPolicy.click();
    }
    public void clickContinue(){
        // solution 1
        btnContinue.click();
        /*
        solution 2:
        btnContinue.submit();
        solution 3:
        Actions act = new Actions(driver);
        act.moveToElement(btnContinue).click().perform();
        solution 4:
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(arguments[0].click();", btnContinue);
        solution 5:
        btnContinue.sendKeys(Keys.RETURN);
        solution 6:
        WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
         */
    }
    public WebElement getConfirmationMsg() {
        return msgConfirmation;
    }
}
