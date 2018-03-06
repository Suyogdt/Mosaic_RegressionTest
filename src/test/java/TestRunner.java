

import Utils.ReadProperties;
import org.junit.runner.RunWith;
import org.junit.AfterClass;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.cucumber.listener.Reporter;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

@RunWith(Cucumber.class)

@CucumberOptions(features="src/test/resources/Feature/",
        //tags = {"@login"},
        glue="classpath:stepDefinations",
        monochrome= true, //dryRun = true,
        plugin="com.cucumber.listener.ExtentCucumberFormatter:src/test/resources/reports/Mosaic_RegressionTestReport.html")


public class TestRunner {

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File(ReadProperties.properties.getProperty("reportConfigPath")));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Machine", 	System.getProperty("os.name") + System.getProperty("os.arch"));
        Reporter.setSystemInfo("Selenium", "3.7.0");
        Reporter.setSystemInfo("Maven", "3.5.2");
        Reporter.setSystemInfo("Java Version", "1.8.0_151");

        /*if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File sourcePath = ts.getScreenshotAs(OutputType.FILE);
                File destinationPath = new File(System.getProperty("user.dir") + "src/test/resources/reports/screenshots/" + screenshotName + ".png");
                Files.copy(sourcePath, destinationPath);
                Reporter.addScreenCaptureFromPath(destinationPath.toString());
            } catch (IOException e) {
            }
        }*/
    }

}
