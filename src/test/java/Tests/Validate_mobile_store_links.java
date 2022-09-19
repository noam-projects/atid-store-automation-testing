package Tests;

import pages.HomePage;
import base.Setup;
import org.testng.annotations.Test;

public class Validate_mobile_store_links {
    public HomePage homepage;
    @Test
    public void validate_home_links()
    {
        homepage=new HomePage(Setup.driver);
        homepage.click_link("https://www.apple.com/app-store/",homepage.app_store);
        homepage.click_link("https://play.google.com/store/",homepage.google_play);
    }
}
