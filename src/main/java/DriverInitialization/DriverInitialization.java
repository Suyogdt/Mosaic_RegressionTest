package DriverInitialization;

import Utils.ReadProperties;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;


public class DriverInitialization {

    public WebDriver driver;
    //public WebDriverWait wait;
    public static ExtentTest logger;
     @SuppressWarnings("deprecation")
    public WebDriver driverInitialization(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeDriverManager.getInstance().setup();
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("start-maximized");
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            driver = new ChromeDriver(capabilities);
            driver.get(ReadProperties.readProperty().getProperty("SIGNINURL"));
        }
        return driver;
    }
}
