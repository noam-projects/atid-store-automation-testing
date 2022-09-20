package Tests;

import base.Setup;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactPage;

public class contage_page_tests {
    public ContactPage contact_page;

    @Test
    public void validate_email_sending() {
        contact_page = new ContactPage(Setup.driver);
        contact_page.input_contact_details("hello_world", "full details", "abcd@gmail.com", "input message");
        try {
            contact_page.input_contact_details("hi", "test", "safajkl11", "test to check");
            Assert.fail();
        } catch (NoSuchElementException e) {
            System.out.println("caught correct exception thus test passed");
        }
        try {
            contact_page.input_contact_details("", "test1", "fasdsa@gmail.com", "123");
            Assert.fail();
        } catch (NoSuchElementException e) {
            System.out.println("caught correct exception thus test passed");
        }
    }
}
