package pages;

import base.ExtentReporter;
import com.aventstack.extentreports.ExtentTest;
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
        ExtentTest reporter= ExtentReporter.test;
        reporter.info("starting input typing test with values "+name+" "+subject+" "+email+" "+ message);
        driver.get(this.url);
        driver.findElement(name_input).sendKeys(name);
        driver.findElement(subject_input).sendKeys(subject);
        driver.findElement(email_input).sendKeys(email);
        driver.findElement(message_input).sendKeys(message);
        reporter.info("added info " + name+" "+subject+" "+email+" "+ message+" to form");
        driver.findElement(submit).click();
        reporter.info("clicked submit");
        Assert.assertEquals(driver.findElement(confirm_message).findElement(By.tagName("p")).getText(),"Thanks for contacting us! We will be in touch with you shortly.");
    }


}

