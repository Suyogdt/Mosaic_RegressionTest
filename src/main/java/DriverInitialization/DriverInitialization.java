package DriverInitialization;

import Utils.ReadProperties;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class DriverInitialization {

	public WebDriver driver;
	//public WebDriverWait wait;
    private ExtentReports extent;
    public ExtentTest logger;
    public Logger log;
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
