package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class RubbishBinPage {

    WebDriver driver;
    Actions actions;

    public RubbishBinPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
    }

    //locators to click on specific file and open options
    String removeFilePart1 = "//td//span[text()='";
    String removeFilePart2 = ".txt']";
    String fileOptions = "/following::td[@class='grid-url-field own-data']";

    //locator to click restore option on file
    By restore = By.xpath("//a[@class='dropdown-item revert-item']");

    //Method to restore file
    public void restoreFile(String FileName) {
        driver.findElement(By.xpath(removeFilePart1+FileName+removeFilePart2+fileOptions)).click();
        driver.findElement(restore).click();
    }
}
