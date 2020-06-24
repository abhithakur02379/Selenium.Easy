package tests.SetUp;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testutilities.ReadConfigProperty;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;

public class SetUpTests {


    ReadConfigProperty config = new ReadConfigProperty();


    @Test
    public void Launch_Application () {

        Configuration.browser = "Edge";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = System.getProperty("user.dir") + "\\test-output";
        open(config.getConfigValues("baseURI"));
        Configuration.timeout = 3000;
        if ($(By.xpath("//*[contains(text(),'Learn selenium to automate with Seleniumeasy.com')]")).exists())
        {
            $(By.xpath("//*[contains(text(),'No, thanks!')]")).click();
        }
    }

}
