package tests.InputForms;

import tests.SetUp.SetUpTests;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reporting.ExtentReport;
import testutilities.ReadConfigProperty;
import testutilities.ReadDataFromExcel;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@Listeners({ScreenShooter.class})
public class SimpleFormTests extends ExtentReport {


    static SetUpTests setup = new SetUpTests();
    static ReadConfigProperty config = new ReadConfigProperty();
    ReadDataFromExcel rdfe;
    {
        try {
            rdfe = new ReadDataFromExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\SeleniumEasy_TestData.xlsx");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test()
    public void SingleInputField() throws Exception {

        String Enter_Message = rdfe.getCellData("InputForms","Enter_Message",2);

        extentTest = extentReports.createTest("singleInputField");

        setup.Launch_Application();

        open(config.getConfigValues("baseURI") + "/basic-first-form-demo.html");
        $(By.id("user-message")).val(Enter_Message);
        $(By.className("btn btn-default")).click();
        Configuration.timeout = 1000;
        $(By.id("display")).selectOptionContainingText(Enter_Message);

    }


    @Test()
    public void TwoInputField() throws Exception {

        extentTest = extentReports.createTest("verifyUserLoginwithValidCredentials");

        setup.Launch_Application();

        open("/basic-first-form-demo");
        $(By.xpath("//*[contains(@name,'username')]")).val(rdfe.getCellData("SimpleFormTests","Username",2));
        $(By.xpath("//*[contains(@name,'password')]")).val(rdfe.getCellData("SimpleFormTests","Password",2)).pressEnter();
        Configuration.timeout = 1000;
        $(By.xpath("//*[contains(text(),'Log Out')]")).shouldBe(Condition.visible);

    }

}




