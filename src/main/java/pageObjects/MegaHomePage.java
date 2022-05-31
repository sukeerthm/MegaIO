package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MegaHomePage {

    WebDriver driver;

    public MegaHomePage(WebDriver driver) {
        this.driver = driver;
    }


    //Locator for login button
    By whiteloginBtn = By.xpath("//button[@class='mega-button top-login-button']");


    //Click on Login button to enter login details
    public void clickLogintoEnterDetails() {
        driver.findElement(whiteloginBtn).click();
    }

    //Method to navigate to given link
    public void navigateTolink(String URL){
        driver.navigate().to(URL);
    }
}
