package tests;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import testutilities.ReadConfigProperty;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SetUpTests {


//    public static final Properties config = new Properties();
    static ReadConfigProperty config = new ReadConfigProperty();

//    public void intialize() throws IOException {
//        config.load(SetUpTests.class.getClassLoader().getResourceAsStream("config.properties"));
//    }

    @Test
    public void launch_Application () throws Exception {

//        config.load(SetUpTests.class.getClassLoader().getResourceAsStream("config.properties"));
        Configuration.browser = "Edge";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = System.getProperty("user.dir") + "\\test-output";
        open(config.getConfigValues("baseURI"));
        Configuration.timeout = 2000;
        if($(By.xpath("//*[contains(text(),'Learn selenium to automate with Seleniumeasy.com')]")).exists())
        {
            $(By.xpath("//*[contains(text(),'No, thanks!')]")).click();
        }
    }

}
