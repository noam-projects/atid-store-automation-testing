package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ContactPage {
    public String url = "https://atid.store/contact-us/";
    public WebDriver driver;
    public By name_input = By.id("wpforms-15-field_0");
    public By subject_input=By.id("wpforms-15-field_5");
    public By email_input=By.id("wpforms-15-field_4");
    public By message_input=By.id("wpforms-15-field_2");
    public By submit=By.id("wpforms-submit-15");
    public By confirm_message=By.id("wpforms-confirmation-15");
    public ContactPage(WebDriver driver)
    {
        this.driver=driver;
    }
    public void input_contact_details(String name,String subject,String email,String message)
    {
        driver.get(this.url);
        driver.findElement(name_input).sendKeys(name);
        driver.findElement(subject_input).sendKeys(subject);
        driver.findElement(email_input).sendKeys(email);
        driver.findElement(message_input).sendKeys(message);
        driver.findElement(submit).click();
        Assert.assertEquals(driver.findElement(confirm_message).findElement(By.tagName("p")).getText(),"Thanks for contacting us! We will be in touch with you shortly.");
    }


}

