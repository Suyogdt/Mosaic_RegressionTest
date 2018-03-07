package CommonActions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;

import DriverInitialization.DriverInitialization;
import com.cucumber.listener.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonUtilities extends DriverInitialization{
    public WebDriver driver;



        public static int generate_randomNumber(int min, int max){
            Random rand = new Random();
            int randomNum = rand.nextInt((max - min) + 1) + min;
            return randomNum;
        }

        public static boolean isElement_PresentOnPage(By elementToPresent , WebDriver driver){
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            try{
                driver.findElement(elementToPresent);
                return true;
            }
            catch(NoSuchElementException e){
                return false;
            }
            catch (Exception e){
                return false;
            }
            finally{
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            }
        }

        public static boolean isElement_DisplayedOnPage(By elementToDisplayed, WebDriver driver){
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            if(driver.findElement(elementToDisplayed).isDisplayed()){
                return true;
            }
            else{
                return false;
            }
        }

        public static void click_ElementOnPage(By elementToClick, WebDriver driver){
            if(isElement_PresentOnPage(elementToClick,driver)){
                new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(elementToClick));
                driver.findElement(elementToClick).click();
            }
            else{
                Assert.fail("FAIL: Location does not found..LOC-"+elementToClick);
            }
        }

        public static void clear_InputBoxOnPage(By textBoxToClear, WebDriver driver){
            if(isElement_PresentOnPage(textBoxToClear,driver)){
                driver.findElement(textBoxToClear).clear();
            }
            else{
                Assert.fail("FAIL: Location does not found..LOC-"+textBoxToClear);
            }
        }
        public static void enterText_InputBoxOnPage(By enterTextElement, String text, WebDriver driver, WebDriverWait wait){
            if(isElement_PresentOnPage(enterTextElement,driver)){
                new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(enterTextElement));
                click_ElementOnPage(enterTextElement,driver);
                clear_InputBoxOnPage(enterTextElement,driver);
                driver.findElement(enterTextElement).sendKeys(text);
            }
            else{
                Assert.fail("FAIL: Location does not found..LOC-"+enterTextElement);
            }
        }

        public static String getText_FromElementOnPage(By elementToGetText, WebDriver driver){
            if(isElement_PresentOnPage(elementToGetText,driver)){
                String text = driver.findElement(elementToGetText).getText();
                return text;
            }
            else{
                Assert.fail("FAIL: Location does not found..LOC-"+elementToGetText);
                return null;
            }
        }

        public static String get_PageTitle(WebDriver driver){
            String title = driver.getTitle();
            return title;
        }

    }


