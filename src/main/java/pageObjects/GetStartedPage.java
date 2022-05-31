package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class GetStartedPage  {

    WebDriver driver;
    Actions actions;
    CommonFunctions comFun;


    public GetStartedPage(WebDriver driver) {
        this.driver = driver;
         actions = new Actions(driver);
        comFun = new CommonFunctions(driver);
    }
    //locator for content screen
    String contentScreen = "//div[@class='megaList-content']";
    //new file locator
    By newTextFile = By.xpath("//span[text()='New text file']");
    //new filename dialog box locator
    By newFilename = By.name("dialog-new-file");
    //Create button locator
    By createFile = By.xpath("//h2[text()='New file']/following::span[text()='Create']");
    //File already exists text locator
    By duplicateFileName = By.xpath("//input[@name='dialog-new-file' and @class='error']");
    //Cancel button locator
    By cancelFileCreation = By.xpath("//h2[text()='New file']/following::span[text()='Cancel']");
    //File Editor locator
    By fileEditor = By.xpath("//div[contains(@class,'CodeMirror')]//textarea");
    //save change button locator
    By saveChanges = By.xpath("//button[@title='Save changes']");
    //save change loading locator
    By saveChangeLoading = By.xpath("//li[@class='status-txt loading']");
    //closeEditor locator
    By closeEditor = By.xpath("//button[@class='close-btn']");
    //Locator to select file on screen
    String selectFilePart1 = "//span[@class='file-block-title' and text()='";
    String selectFilePart2 = ".txt']";
    //Remove file locator
    By removeFile = By.xpath("//i[@class='sprite-fm-mono icon-bin']");
    //Confirm delete locator
    By confirmDelete = By.xpath("//button[@class='mega-button positive confirm']/span[text()='Yes']");
    //Locator for rubbish bin tab
    By rubbishBin = By.xpath("//button[@data-link='bin']");


    //Steps to create new file
    public boolean createNewFile(String FileName){
        actions.contextClick(driver.findElement(By.xpath(contentScreen))).perform();
        driver.findElement(newTextFile).click();
        driver.findElement(newFilename).sendKeys(FileName);
        driver.findElement(createFile).click();
        if (driver.findElements(duplicateFileName).size() ==0) {
            return true;
        }
        return false;
    }

    //Cancel file creation
    public void cancelFileCreation(){
        driver.findElement(cancelFileCreation).click();
    }

    //Add content to the file
    public void addContenttoFile(String Content){
        driver.findElement(fileEditor).sendKeys(Content);
        driver.findElement(saveChanges).click();
        comFun.checkElementInVisibility(saveChangeLoading,10);
    }

    //Verify if the created file exists on the screen
    public void verifyFileExists(String FileName){
        Boolean Display = driver.findElement(By.xpath(selectFilePart1+FileName+selectFilePart2)).isDisplayed();
        Display.equals(Boolean.TRUE);
    }

    //Close the file editor
    public void closeFileEditor(){
        driver.findElement(closeEditor).click();
    }

    //Steps to delete the file
    public void deleteFile(String FileName){
        actions.contextClick(driver.findElement(By.xpath(selectFilePart1+FileName+selectFilePart2))).perform();
        driver.findElement(removeFile).click();
        driver.findElement(confirmDelete).click();
    }

    //Verify file deleted from the drive
    public void verifyFileDeleted(String FileName){
        Boolean Display = driver.findElement(By.xpath(selectFilePart1+FileName+selectFilePart2)).isDisplayed();
        Display.equals(Boolean.FALSE);
    }

    //Go to rubbish bin tab
    public void openRubbishBin(){
        driver.findElement(rubbishBin).click();
    }
}
