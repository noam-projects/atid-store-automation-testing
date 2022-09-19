package Tests;

import base.Setup;
import org.testng.annotations.Test;
import pages.HomePage;
public class Validate_home_links_test {
    public HomePage homepage;
    @Test
    public void validate_home_links()
    {
        homepage=new HomePage(Setup.driver);
        homepage.click_link("https://atid.store/",homepage.home);
        homepage.click_link("https://atid.store/about/",homepage.about);
        homepage.click_link("https://atid.store/cart-2/",homepage.cart);
        homepage.click_link("https://atid.store/contact-us/",homepage.contact_us);
        homepage.click_link("https://atid.store/Women-jeans/",homepage.women_Jeans);
    }
}
