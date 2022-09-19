package pages;

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
        public HomePage(WebDriver driver)
        {
            this.driver=driver;
        }
        public void click_link(String valid_title,By elem)
        {
            driver.get("https://atid.store/");
            driver.findElement(elem).click();
            String title=driver.getCurrentUrl();
            Assert.assertEquals(title,valid_title);
        }
}
