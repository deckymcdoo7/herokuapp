package pages;

import driverHelper.DriverHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {
    public static int answerValue;
    private static Logger logger = Logger.getLogger(HomePage.class);
    public static ArrayList<String> urls = new ArrayList<String>();
    DriverHelper driverHelper = new DriverHelper();
    WebDriver driver;

    @FindBy(xpath = "//a[@class='button']")
    private WebElement mainButton;

    @FindBy(xpath = "//a[@class='button alert']")
    private WebElement alertButton;

    @FindBy(xpath = "//a[@class='button success']")
    private WebElement successButton;

    @FindBy(xpath = "//img[contains(@alt, 'Fork me on GitHub')]")
    private WebElement githubBanner;

    @FindBy(xpath = "//script[contains(text(),'strokeText')]")
    private WebElement canvas;

    @FindBy(partialLinkText = "Elemental Selenium")
    private WebElement elementSeleniumLink;

    @FindBy(className = "table")
    private WebElement table;

    public HomePage(WebDriver driver) {
        logger.info("Initializing Home Page Objects");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomeUrl() {
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
        driverHelper.waitForVisibilityOf(mainButton, driver);
    }

    public void clickGithubBanner() {
        githubBanner.click();
    }

    public int getAnswerValue() {
        String entireDom = getEntireDom();
        Pattern r = Pattern.compile("(?<=Answer: )(.*)(?=')");
        Matcher m = r.matcher(entireDom);
        int answerValue = 0;
        while (m.find()) {
           answerValue = Integer.parseInt(m.group(1));
        }
        return answerValue;
    }

    public void storeAnswerValue() {
        answerValue = getAnswerValue();
    }

    public void scrollToElementalSelenium() {
        JavascriptExecutor js =(JavascriptExecutor) driver;
        logger.info("Executing javascript");
        js.executeScript("arguments[0].scrollIntoView(false);", elementSeleniumLink);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void clickAlertButton() {
        alertButton.click();
    }

    public void clearURlList() { urls.clear(); }

    public void clickAnswerButton(String button) {
        if (button.contains("mainButton")) {
            mainButton.click();
        } else if (button.contains("alertButton")) {
            alertButton.click();
        } else if (button.contains("successButton")) {
            successButton.click();
        }
    }

    public void clickElementSeleniumLink() {
        elementSeleniumLink.click();

    }

    public void storeClickedLinkURL(String urlText) {
        List<WebElement> links = getAllLinks(urlText);
        for (WebElement editButton : links) {
            editButton.click();
            urls.add(driver.getCurrentUrl());
        }
    }

    public boolean isTableColumnOrdered(String column) {
        Map<String, Integer> headerMap = new HashMap<String, Integer>();
        List<WebElement> headerList = driver.findElements(By.xpath("//thead/tr/th"));

        for (int i = 0 ; i < headerList.size(); i++) {
            headerMap.put(driver.findElement(By.xpath(String.format("//thead/tr/th[%s]", i+1 ))).getText(), i + 1);
        }

        List<WebElement> tableList = driver.findElements(By.xpath("//table/tbody/tr"));
        ArrayList<String> columnOrder = new ArrayList<String>();
        for(int i = 0; i < tableList.size(); i ++) {
            tableList.get(i);
            columnOrder.add(driver.findElement(By.xpath(String.format("//table/tbody/tr[%s]/td[%s]", i + 1, headerMap.get(column)))).getText());
        }

        return isSorted(columnOrder);
    }

    private List<WebElement> getAllLinks(String name) {
        List<WebElement> links = driver.findElements(By.linkText(name));
        return links;
    }

    private boolean isSorted(ArrayList<String> list)
    {
        boolean sorted = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
        }

        return sorted;
    }

    private String getEntireDom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        logger.info("Executing javascript");
        return js.executeScript("return document.documentElement.outerHTML").toString();
    }
}
