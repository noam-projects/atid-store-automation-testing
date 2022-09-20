package Utils;

import base.Setup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Screenshots {
    public static void captureScreenshot() throws IOException {
        Date d = new Date();
        String fileName;
        fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
        File screenshot = ((TakesScreenshot) Setup.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\screenshots\\"+fileName));
    }
}
