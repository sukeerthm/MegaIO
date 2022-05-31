package stepDef;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObjects.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class stepdefinitions {
    String currentWorkingDir = System.getProperty("user.dir");
    String downloadPath = currentWorkingDir+"\\src\\test\\java\\linuxDownloads";
    private final Properties properties;
    WebDriver driver;
    MegaHomePage home;
    LoginPage loginpage;
    GetStartedPage getstartedpage;
    RubbishBinPage rubbishbin;
    MegaDesktopAppPage megadesktopapp;
    ChromeOptions options;


    public stepdefinitions(){
        BufferedReader reader;
        String propertyFilePath = currentWorkingDir + "\\src\\test\\java\\configDetails\\cofiguration.properties";
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }


    @Before
    public void testSetup() throws InterruptedException {

        if(properties.getProperty("browser").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", currentWorkingDir + properties.getProperty("chromeDriver"));

            //Declare chrome options to set download directory
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", downloadPath);
            options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(options);

        } else if(properties.getProperty("browser").equals("firefox")){
            System.setProperty("webdriver.gecko.driver", currentWorkingDir + properties.getProperty("firefoxDriver"));
            driver = new FirefoxDriver();
        } else if ((properties.getProperty("browser").equals("IE"))) {
            System.setProperty("webdriver.ie.driver", currentWorkingDir + properties.getProperty("IEDriver"));
            driver=new InternetExplorerDriver();
        }

        this.driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.get(properties.getProperty("megaURL"));

        //Initialize page objects
        home = new MegaHomePage(driver);
        loginpage=  new LoginPage(driver);
        getstartedpage = new GetStartedPage(driver);
        rubbishbin = new RubbishBinPage(driver);
        megadesktopapp = new MegaDesktopAppPage(driver);
    }

    @After
    public void testTeardown() {
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
        this.driver=null;
    }

    @Given("I login to mega website using email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void i_login_to_mega_website(String email, String password) {
    home.clickLogintoEnterDetails();
    loginpage.enterDetailsAndLogin(email,password);
    }
    @Then("I create new text file \"([^\"]*)\" with content \"([^\"]*)\"$")
    public void i_create_new_text_file(String filename, String content) {
      boolean fileCreated = getstartedpage.createNewFile(filename);
      if(fileCreated){
          getstartedpage.addContenttoFile(content);
          getstartedpage.closeFileEditor();
      } else{
          getstartedpage.cancelFileCreation();
      }
    }
    @And("I verify file {string} is created")
    public void i_verify_file_is_created(String filename) {
        getstartedpage.verifyFileExists(filename);
    }

    @And("I Delete the file \"([^\"]*)\"$")
    public void i_delete_the_file(String filename) {
        getstartedpage.deleteFile(filename);
    }
    @Then("I verify File {string} is deleted")
    public void i_verify_file_is_deleted(String filename) {
        getstartedpage.verifyFileDeleted(filename);
    }

    @Then("I navigate to rubbish bin folder")
    public void i_navigate_to_rubbish_bin_folder() {
        getstartedpage.openRubbishBin();
    }
    @Then("I restore the deleted file {string} from rubbish bin folder")
    public void i_restore_the_deleted_file_from_rubbish_bin_folder(String filename) {rubbishbin.restoreFile(filename);
    }

    @Then("I verify file {string} is restored successfully")
    public void i_verify_file_is_restored_successfully(String filename) {
        getstartedpage.verifyFileExists(filename);
    }


    //Step Definitions to validate all the Linux Distros in the list are downloadable (when you click download, the links for downloading should be working properly)
    @Given("I navigate to {string}")
    public void i_navigate_to(String URL) { home.navigateTolink(URL);
    }
    @Then("I Click on button {string}")
    public void i_click_on_button(String platform) {megadesktopapp.selectPlatform(platform);
    }
    @Then("validate all the Linux versions in the list are downloadable")
    public void validate_all_the_linux_versions_in_the_list_are_downloadable() throws InterruptedException {
        megadesktopapp.emptyDownloadDir(downloadPath);
        megadesktopapp.selectLinuxVersionToDownload(downloadPath);
    }

}
