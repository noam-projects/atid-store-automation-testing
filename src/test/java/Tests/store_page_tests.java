package Tests;

import base.Setup;
import org.testng.annotations.Test;
import pages.StorePage;

public class store_page_tests {
    public StorePage store_page;
    @Test
    public void validate_sorting()
    {
        store_page=new StorePage(Setup.driver);
        store_page.check_sorting();
    }
    @Test
    public void validate_price_filtering()
    {
        store_page=new StorePage(Setup.driver);
        store_page.move_price_filter_slider(1);
        store_page.move_price_filter_slider(3);

    }

}
