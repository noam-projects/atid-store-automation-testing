package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ExtentReporter {
    public static ExtentSparkReporter html_reporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeSuite
    public void report_settings() {
        html_reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports and logs\\extent.html");
        html_reporter.config().setEncoding("utf-8");
        html_reporter.config().setDocumentTitle("SUITE");
        html_reporter.config().setReportName("test results");
        html_reporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(html_reporter);
        extent.setSystemInfo("tester name", "noam");
        Setup.LOGGER.info("extent reporter set up done");
    }
}
