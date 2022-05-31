package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locator for email field
    By userEmail = By.id("login-name2");

    //Locator for password field
    By userPassword = By.id("login-password2");

    //Locator for login button
    By loginBtn = By.xpath("//button[@class='mega-button positive login-button large right']");

    //Method to enter username
    public void enterUsername(String userName) {
        driver.findElement(userEmail).sendKeys(userName);
    }
    //Method to enter password
    public void enterPassword(String passWord) {
        driver.findElement(userPassword).sendKeys(passWord);
    }
    //Method to click on Login button
    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    //Method for login
    public void enterDetailsAndLogin(String userName,String passWord ){
        enterUsername(userName);
        enterPassword(passWord);
        clickLogin();
    }
}
