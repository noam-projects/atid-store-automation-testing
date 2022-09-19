package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StorePage {
    public String url = "https://atid.store/store/";
    public By items_rating = By.xpath("//*[@id=\"main\"]/div/ul/li/div[2]/div/span/strong");
    public By items_price = By.xpath("//*[@id=\"main\"]/div/ul/li/div[2]/span[2]/span/bdi");
    public By items_price_in_sale=By.xpath("//*[@id=\"main\"]/div/ul/li/div[2]/span[2]/ins/span/bdi");
    public By getPrice_slider_left = By.xpath("//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[1]/span[1]");
    public By price_slider_right = By.xpath("//*[@id=\"woocommerce_price_filter-2\"]/form/div/div[1]/span[2]");
    public By filter = By.cssSelector("#woocommerce_price_filter-2 > form > div > div.price_slider_amount > button");
    public By from_price_range = By.className("from");
    public By to_price_range = By.className("to");
    WebDriver driver;

    public StorePage(WebDriver driver) {
        this.driver = driver;
    }

    public void check_sorting() {
        driver.get(this.url);
        WebElement sorting_selection = driver.findElement(By.name("orderby"));
        Select selectObject = new Select(sorting_selection);
        selectObject.selectByValue("rating");
        List<WebElement> store_items = driver.findElements(items_rating);
        if (store_items == null)
            Assert.fail("didn't find any items");
        for (int i = 0; i < store_items.size(); i++) {
            try {
                Float.parseFloat(store_items.get(i).getDomProperty("textContent"));
            } catch (NumberFormatException nfe) {
                Assert.fail("the current item rating is not a number");
            }
        }
        int sorting_size = store_items.size() - 1;
        for (int i = 1; i < sorting_size; i++) {
            if (Float.parseFloat(store_items.get(i).getDomProperty("textContent")) > Float.parseFloat(store_items.get(i - 1).getDomProperty("textContent")))
                Assert.fail("items are not sorted correctly");
        }
    }

    public void move_price_filter_slider(int intervals) {
        driver.get(this.url);
        WebElement price_slider_right_part = driver.findElement(price_slider_right);
        new Actions(driver).dragAndDropBy(price_slider_right_part, -11 * intervals, 0).perform();
        WebElement price_slider_left_part = driver.findElement(getPrice_slider_left);
        new Actions(driver).dragAndDropBy(price_slider_left_part, 11 * intervals, 0).perform();
        driver.findElement(filter).click();
        int min_price = 0;
        int max_price = 0;
        String line = driver.findElement(from_price_range).getText();
        Pattern decimalNumPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = decimalNumPattern.matcher(line);
        if (matcher.find())
            min_price = Integer.parseInt(matcher.group());
        else
            Assert.fail("no number is found in price range");
        line = driver.findElement(to_price_range).getText();
        matcher = decimalNumPattern.matcher(line);
        if (matcher.find())
            max_price = Integer.parseInt(matcher.group());
        else
            Assert.fail("no number is found in price range");
        List<WebElement> store_items = driver.findElements(items_price);
        List <WebElement> store_items_in_sale=driver.findElements(items_price_in_sale);
        store_items.addAll(store_items_in_sale);
        if (store_items == null)
            Assert.fail("didn't find any items");
        for (int i = 0; i < store_items.size(); i++) {
            try {
                line = store_items.get(i).getText();
                decimalNumPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
                matcher = decimalNumPattern.matcher(line);
                float price;
                matcher.find();
                price = Float.parseFloat(matcher.group());
                if(price>max_price||price<min_price)
                    Assert.fail("filter did not work correctly");
            } catch (NumberFormatException nfe) {
                Assert.fail("the current item price is not a number");
            }
        }
    }
}
