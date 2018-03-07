package stepDefinations;

import CommonActions.CommonUtilities;
import DriverInitialization.DriverInitialization;
import PageObjects.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import paulhammant.ngwebdriver.ByAngular;

public class LoginPage_StepDef extends DriverInitialization {

    public WebDriver driver;
    public LoginPage loginPage;

    @Parameters("browser")
    @Given("^I am on Login page$")
    public void i_am_on_Login_page() {

        DriverInitialization di = new DriverInitialization();
        driver = di.driverInitialization("chrome");

        Assert.assertTrue(driver.getTitle().equals("MOSAIC"));
    }

    @When("^I enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iEnterUserNameAndPassword(String uname , String password) throws Throwable {
        loginPage=new LoginPage(driver);
        loginPage.enterLoginDetails(uname,password);
        loginPage.clickSignInbtn();

    }

    @Then("^I should be able to Landing page of Mosaic$")
    public void i_should_be_able_to_Landing_page_of_Mosaic() throws Throwable {
        System.out.println("I am landing page");
        //Assert.assertTrue(CommonUtilities.isElement_PresentOnPage(By.xpath("//button[text()='+New Project']"),driver));
    }
}
