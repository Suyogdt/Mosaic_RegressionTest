package PageObjects;

import CommonActions.CommonUtilities;
import DriverInitialization.DriverInitialization;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import paulhammant.ngwebdriver.ByAngular;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage extends DriverInitialization{


    public static final String login_duplicatesessionAlert = "//*[@id='app']/div[3]/div/div";
    public static final String login_acceptDuplicatesessionAlert = "//*[@id='app']/div[3]/div/div/div[3]/button[1]";
    public By signInBtn= By.xpath("//a[contains(text(),'Sign-in')]");

    public By userName=By.xpath("//input[@placeholder='Email Id']");
    public By password=By.xpath("//input[@placeholder='Password']");

    public By submit= ByAngular.buttonText("Submit");
    public By errorMessage=By.xpath(".//*[@id='content']/div/div[2]/div/div/form/fieldset/div[3]/span");
    public By userProfile=By.xpath("//li[contains(@class,' user-profile ')]/a");
    public By userProfile_ULList=By.xpath("//li[contains(@class,' user-profile ')]/ul");
    public By userProfile_List=By.xpath("//li[contains(@class,' user-profile ')]/ul/li");

    WebDriver driver;
    CommonUtilities cu = new CommonUtilities();

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public String pageTitle(){
        return driver.getTitle();
    }

    //******************************************* HOME PAGE CHANGES START**********************************************
    public boolean isSignInBtnDisplayed(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(signInBtn));
        return driver.findElement(signInBtn).isDisplayed();
    }

    public void clickSignInbtn(){
        //new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(signInBtn));
        //driver.findElement(signInBtn).click();

        if(cu.isElement_PresentOnPage(submit,driver)){
            cu.click_ElementOnPage(submit,driver);
        }

    }

    public boolean isSignInPageDisplayed() throws InterruptedException{
        Thread.sleep(3000);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(userName));
        return driver.findElement(userName).isDisplayed();
    }

    //******************************************* HOME PAGE CHANGES END**********************************************

    public void clearTextBoxes(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(userName));

        driver.findElement(userName).clear();
        driver.findElement(password).clear();
    }

    public boolean isPasswordDisplayed(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(password));
        return driver.findElement(password).isDisplayed();
    }

    public boolean isUserNameDisplayed(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(userName));
        return driver.findElement(userName).isDisplayed();
    }

    public boolean isSubmitDisplayed(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(submit));
        return driver.findElement(submit).isDisplayed();
    }

    public void enterLoginDetails(String user_Name,String passwd)
    {
        try
        {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(userName));
            driver.findElement(userName).sendKeys(user_Name);
            driver.findElement(password).sendKeys(passwd);
        }catch (Exception e) {
            System.out.println("Error msg is: " + e.getMessage());
        }
    }

    public void clickSignInButton(){
        driver.findElement(submit).click();
    }

    public boolean checkerrorOrAlert(){
        if(driver.findElement(errorMessage).isDisplayed()){
            return true;
        }else if(driver.findElement(By.xpath(login_duplicatesessionAlert)).isDisplayed()){
            isAlertPresent();
            return true;
        }
        return false;
    }

    public String errorMessageString(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    public void isAlertPresent() {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (driver.findElement(By.xpath(login_duplicatesessionAlert)).isDisplayed())
            {
                driver.findElement(By.xpath(login_acceptDuplicatesessionAlert)).click();
            }else{
                System.out.println("Login Successful");
            }
        }
        catch (Exception e) {
            System.out.println("Error message is" +e.getMessage() );
        }
    }

    public boolean isDisplayed_UserProfile(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(userProfile));
        return driver.findElement(userProfile).isDisplayed();
    }

    public void click_UserProfile(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(userProfile));
        driver.findElement(userProfile).click();
    }

    public List<String> get_UserProfileOptions_LogOut(){
        List<String> tabList=new ArrayList<String>();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(userProfile_ULList));
        WebElement tabListElement = driver.findElement(userProfile_ULList);
        //Declaring List of WebElements to get List of Tabs
        List<WebElement> presetTabList=tabListElement.findElements(userProfile_List);
        //Iterator to iterate list elements
        Iterator<WebElement> it=presetTabList.iterator();
        while (it.hasNext()) {
            WebElement listElement = (WebElement) it.next();

            //Adding value of WebElement to ArrayList
            tabList.add(listElement.getText());
            if (listElement.getText().equals("Log out")){listElement.click();}
            //Printing List Element
            System.out.println(listElement.getText());
        }
        return tabList;
    }
}
