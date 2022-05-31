package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class MegaDesktopAppPage {


    WebDriver driver;

    public MegaDesktopAppPage(WebDriver driver) {
        this.driver = driver;
    }

    //platform locator xpaths
    By linuxOS = By.xpath("//a[@data-os='linux']");
    By mac = By.xpath("//a[@data-os='mac']");
    By windows = By.xpath("//a[@data-os='windows']");

    //Locator to click on dropdown
    By clickLinuxVersionDropdown = By.xpath("//i[@class='sprite-fm-mono icon-dropdown']");
    //Locator to see options under dropdown
    By availableVersions = By.xpath("//div[@class='dropdown-scroll ps ps--theme_default ps--active-y']//div[@class='option']");
    //32bit availability check locator
    By bit32displayed = By.xpath("//label[text()='32-bit']");
    //32bit radio button locator
    By bit32RadioButton = By.xpath("//input[@type='radio' and @value='32']");
    //64bit radio button locator
    By bit64RadioButton = By.xpath("//input[@type='radio' and @value='64']");
    //Download button locator
    By downloadButton = By.xpath("//button[contains(@class,'megaapp-linux-download')]");

    //Method to select the platform
    public void selectPlatform(String platform) {
        switch (platform) {
            case "Linux" -> driver.findElement(linuxOS).click();
            case "mac" -> driver.findElement(mac).click();
            case "windows" -> driver.findElement(windows).click();
        }
    }

    //Script to download all the linux versions both 32 and 64 bit
    public void selectLinuxVersionToDownload(String downloadPath) throws InterruptedException {
        driver.findElement(clickLinuxVersionDropdown).click();
        List<WebElement> newofVersions = driver.findElements(availableVersions);
        for(WebElement linuxVersions : newofVersions) {
            linuxVersions.click();
            String is32BitAvailable = driver.findElement(bit32displayed).getAttribute("style");
            if(is32BitAvailable.isEmpty()){
                driver.findElement(bit32RadioButton).click();
                String donwloadLink32 = driver.findElement(downloadButton).getAttribute("data-link");
                String fileName = donwloadLink32.substring(donwloadLink32.lastIndexOf('/') + 1);
                driver.findElement(downloadButton).click();
                Assert.assertTrue("Failed to download 32 Bit", isPackageDownloaded(downloadPath,fileName));
            }
            driver.findElement(bit64RadioButton).click();
            String donwloadLink64 = driver.findElement(downloadButton).getAttribute("data-link");
            String fileName = donwloadLink64.substring(donwloadLink64.lastIndexOf('/') + 1);
            driver.findElement(downloadButton).click();
            Assert.assertTrue("Failed to download 64 Bit", isPackageDownloaded(downloadPath,fileName));
            driver.findElement(clickLinuxVersionDropdown).click();
        }
    }

    //Script to empty the download before
    public void emptyDownloadDir(String downloadPath) {
        File file = new File(downloadPath);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files)
                f.delete();
        }
    }

    //Script to check if the packages are downloaded
    public boolean isPackageDownloaded( String downloadPath,String fileName) {
        File[] listOfFiles;
        boolean fileDownloaded = false;
        String filePath = null;
        long waitTillTime = Instant.now().getEpochSecond() + 30;
        while (Instant.now().getEpochSecond() < waitTillTime) {
            listOfFiles = new File(downloadPath).listFiles();
            assert listOfFiles != null;
            for (File file : listOfFiles) {
                if (file.getName().contains(fileName)) {
                    fileDownloaded = true;
                    break;
                }
            }
            if (fileDownloaded) {
                break;
            }
        }
        return fileDownloaded;
    }
}
