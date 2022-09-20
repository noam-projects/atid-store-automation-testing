package pages;

import base.ExtentReporter;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage
{
        WebDriver driver;
         public By home=By.cssSelector("#menu-item-423 > a");
         public By about=By.cssSelector("#menu-item-387 > a");
         public By cart=By.cssSelector("#menu-item-389 > a");
         public By contact_us=By.cssSelector("#menu-item-425 > a");
         public By women_Jeans=By.cssSelector("#menu-item-665 > a");
         public By app_store=By.cssSelector("#media_image-1 > img");
         public By google_play=By.cssSelector("#media_image-2 > img");
         public String url="https://atid.store/";
        public HomePage(WebDriver driver)
        {
            this.driver=driver;
        }
        public void click_link(String valid_title,By elem)
        {
            ExtentTest reporter= ExtentReporter.test;
            reporter.info("starting link clicking test with values "+ valid_title+" "+elem);
            driver.get(this.url);
            reporter.info("navigted to "+this.url);
            driver.findElement(elem).click();
            reporter.info("clicked on "+elem);
            String title=driver.getCurrentUrl();
            Assert.assertEquals(title,valid_title);
        }
}
