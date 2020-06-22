package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SetUpTests {


    public static final Properties config = new Properties();

    @BeforeTest(description = "Test to verify application launched successfully")
    public void launch_ParaBank () throws Exception {

        config.load(SetUpTests.class.getClassLoader().getResourceAsStream("config.properties"));
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\Drivers\\msedgedriver.exe");
        System.setProperty("selenide.browser", "Edge");
        Thread.sleep(1000);
        Configuration.startMaximized = true;
        Configuration.reportsFolder = System.getProperty("user.dir") + "\\test-output";
        open(config.getProperty("baseURI"));
        Thread.sleep(1000);
        $(By.xpath("//a[contains(text(),'ParaBank')]")).shouldBe(Condition.visible);
    }



}
