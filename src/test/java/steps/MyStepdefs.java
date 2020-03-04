package steps;

import driverHelper.DriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MyStepdefs {

    WebDriver driver;
    DriverHelper driverHelper = new DriverHelper();
    HomePage homePage;

    @Before
    public void setup() {
        this.driver = driverHelper.startDriver();
        homePage = new HomePage(this.driver);
    }

    @Given("I navigate to Herokuapp challenge dom page")
    public void navigateToHomePage() {
        homePage.navigateToHomeUrl();
    }

    @When("^I click fork me on github banner")
    public void clickGithubBanner() {
        homePage.clickGithubBanner();
    }

    @When("I click Elemental Selenium link")
    public void clickElementalLink() {
        homePage.clickElementSeleniumLink();
    }

    @When("^I click the Alert Button")
    public void clickAlertButton() {
        homePage.clickAlertButton();
    }

    @When("I click the {string} button")
    public void clickAnswerButton(String button) {
        homePage.clickAnswerButton(button);
    }

    @When("I click the {string} button for each row")
    public void clickTableEdit(String urlText) {
       homePage.storeClickedLinkURL(urlText);
    }

    @And("I scroll to Elemental Selenium link")
    public void scrollToElement() {
        homePage.scrollToElementalSelenium();
    }

    @And("I store the answer value")
    public void storeAnswerValue() {
        homePage.storeAnswerValue();
    }

    @Then("The URL is appended with {string}")
    public void testULContent(String urlText) {
        ArrayList<String> urls = homePage.urls;
        for (int i = 0; i < urls.size(); i++ ) {
            MatcherAssert.assertThat(urls.get(i), CoreMatchers.is(String.format("https://the-internet.herokuapp.com/challenging_dom#%s", urlText)));
        }
        homePage.clearURlList();
    }

    @Then("^I am taking to the selenium page")
    public void assertSeleniumPage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ArrayList<String> currentTabs = new ArrayList<String>();
        for (String handle : driver.getWindowHandles()) {
            currentTabs.add(handle);
        }

        driver.switchTo().window(currentTabs.get(1));
        String newTabUrl = driver.getCurrentUrl();
        MatcherAssert.assertThat(newTabUrl, CoreMatchers.is("http://elementalselenium.com/"));
    }

    @Then("The answer value should change")
    public void checkChangingAnswerValue() {
        int oldAnswer = homePage.answerValue;
        MatcherAssert.assertThat(oldAnswer, CoreMatchers.not(homePage.getAnswerValue()));
    }

    @Then("^I am taking to the github page")
    public void assertGithubPage() {
        MatcherAssert.assertThat(homePage.getCurrentURL(), CoreMatchers.is("https://github.com/tourdedave/the-internet"));
    }

    @Then("The column {string} in the table should be ordered")
    public void checkTableColumnOrdered(String column){
        homePage.isTableColumnOrdered(column);
    }

    @After
    public void quitDriver() {
        driverHelper.quitDriver(driver);
    }
}
