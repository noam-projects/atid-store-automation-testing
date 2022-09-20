package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Setup
{
    public static WebDriver driver;
    public Properties main_config = new Properties();
    public static final Logger LOGGER = Logger.getLogger(Setup.class.getName());
    public FileInputStream fis;
    public Handler fileHandler = null;
    public String screen_shot_name;
@BeforeSuite
    public void preparations()
{
    try {
        fileHandler = new FileHandler(System.getProperty("user.dir")+"\\reports and logs\\logs.log");
        LOGGER.addHandler(fileHandler);
        fileHandler.setLevel(Level.ALL);
        LOGGER.setLevel(Level.ALL);
        LOGGER.info("now logs to" + System.getProperty("user.dir")+"\\reports and logs\\logs.log");
    } catch (IOException exception) {
        LOGGER.log(Level.SEVERE, "cannot open/read file handler cannot continue must crash");
        System.exit(1);
    }
    LOGGER.info("starting suite");
    try {
        fis = new FileInputStream(System.getProperty("user.dir") + "\\resources\\Config.properties");
    } catch (
            FileNotFoundException e) {
        LOGGER.severe("cannot find/read config file, cannot continue must crash ");
        System.exit(1);
    }
    try {
        main_config.load(fis);
        LOGGER.info("config file loaded");
    } catch (IOException e) {
        e.printStackTrace();
    }
    if (main_config.getProperty("browser").equals("firefox")) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        LOGGER.info("firefox started");

    } else if (main_config.getProperty("browser").equals("chrome")) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("chrome started");
    }
    driver.get(main_config.getProperty("Test_URL"));
    LOGGER.info("navigated to to : " + main_config.getProperty("Test_URL"));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

}
    @AfterSuite
    public void quitDriver()
    {
        driver.quit();
    }
}
