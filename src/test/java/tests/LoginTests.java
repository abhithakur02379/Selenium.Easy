package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.ScreenShooter;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import reporting.ExtentReport;
import testutilities.ReadDataFromExcel;

import static com.codeborne.selenide.Selenide.$;


@Listeners({ScreenShooter.class})
public class LoginTests extends ExtentReport {

    SetUpTests setup = new SetUpTests();
    ReadDataFromExcel rdfe;
    {
        try
        {
            rdfe = new ReadDataFromExcel(System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\ParaBank_TestData.xlsx");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    @Test(priority = 3, description = "Customer login with Valid Credentials")
    public void verifyUserLoginwithValidCredentials() throws Exception {

        extentTest = extentReports.createTest("verifyUserLoginwithValidCredentials");

        setup.launch_ParaBank();

        $(By.xpath("//*[contains(@name,'username')]")).val(rdfe.getCellData("LoginTests","Username",2));
        $(By.xpath("//*[contains(@name,'password')]")).val(rdfe.getCellData("LoginTests","Password",2)).pressEnter();
        Thread.sleep(2000);
        $(By.xpath("//*[contains(text(),'Log Out')]")).shouldBe(Condition.visible);

    }


    @Test(priority = 1, description = "Customer login with Invalid Credentials")
    public void verifyUserLoginwithInvalidCredentials() throws Exception {

        extentTest = extentReports.createTest("verifyUserLoginwithInvalidCredentials");

        setup.launch_ParaBank();

        $(By.xpath("//*[contains(@name,'username')]")).val(rdfe.getCellData("LoginTests","Username",3));
        $(By.xpath("//*[contains(@name,'password')]")).val(rdfe.getCellData("LoginTests","Password",3)).pressEnter();
        Thread.sleep(1000);
        $(By.xpath("//*[contains(text(),'The username and password could not be verified.')]")).shouldBe(Condition.visible);

    }


    @Test(priority = 2, description = "Customer login with Empty Credentials")
    public void verifyUserLoginwithEmptyCredentials() throws Exception {

        extentTest = extentReports.createTest("verifyUserLoginwithEmptyCredentials");

        setup.launch_ParaBank();

        $(By.xpath("//*[contains(@name,'username')]")).val(rdfe.getCellData("LoginTests","Username",4));
        $(By.xpath("//*[contains(@name,'password')]")).val(rdfe.getCellData("LoginTests","Password",4)).pressEnter();
        Thread.sleep(1000);
        $(By.xpath("//*[contains(text(),'Please enter a username and password.')]")).shouldBe(Condition.visible);

    }





}




