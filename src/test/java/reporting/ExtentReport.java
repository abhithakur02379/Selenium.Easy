package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    private static TakesScreenshot Screenshot;
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    @BeforeTest
    public void setExtent()
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output\\ParaBank_Report.html");
        htmlReporter.config().setDocumentTitle("ParaBank Automation Test Report");
        htmlReporter.config().setReportName("ParaBank Functionl Report");
        htmlReporter.getStartTime();
        htmlReporter.getEndTime();
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().getTimeStampFormat();

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("HostName", "Local Host");
        extentReports.setSystemInfo("Tester", "Abhishek Singh");
        extentReports.setSystemInfo("OS", "Windows10");
        extentReports.setSystemInfo("Browser", "MS Edge");
    }

    @AfterTest
    public void endReport() {

        extentReports.flush();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS)
        {
            extentTest.log(Status.PASS, result.getName() + " is PASSED");
        }
        else if (result.getStatus() == ITestResult.SKIP)
        {
            extentTest.log(Status.SKIP, result.getName() + " is SKIPPED");
        }
        else if (result.getStatus() == ITestResult.FAILURE)
        {
            extentTest.log(Status.FAIL, result.getName() + " is FAILED");     //add name in extent report
            extentTest.log(Status.FAIL, result.getThrowable() + " is FAILED");    //add error/exception in extent report
//            String screenshotPath = getScreenshot(result.getName());
//            Selenide.screenshot(System.getProperty("user.dir") + "\\test-output\\Screenshots");
        }
    }

    public static String getScreenshot(String screenshotName) throws IOException {

        String dateName = new SimpleDateFormat("yyyy/MM//dd").format(new Date());
        File source = Screenshot.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

}
