package driverHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHelper {
    public static final int PAGE_LOAD_TIMEOUT = 70;

    public static WebDriver driver;


    public void quitDriver(WebDriver driver) {
        driver.quit();
    }

    public WebDriver startDriver() {
        if (System.getProperty("browser") == null ) {
            return startChromeDriver();
        }
        if (System.getProperty("browser").contains("firefox")) {
            return startFirefoxDriver();
        } else {
            return startChromeDriver();
        }
    }

    public WebDriver startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        WebDriver wd = new ChromeDriver();
        return wd;
    }

    public WebDriver startFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver","/usr/local/bin/geckodriver");
        WebDriver wd = new FirefoxDriver();
        return wd;
    }

    public void waitForVisibilityOf(WebElement webElement, WebDriver driver) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, PAGE_LOAD_TIMEOUT);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
