package com.w2a.datadriven.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExtentManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

	/*
     * WebDriver
	 * logs
	 * properties
	 * excel
	 * db
	 * mail
	 * Extent Reports
	 */

    public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
    public static ThreadLocal<ExtentTest> exTest = new ThreadLocal<ExtentTest>();
    public static String screenshotPath;
    public static String screenshotName;
    static WebElement dropdown;
    public RemoteWebDriver driver = null;
    public Properties OR = new Properties();
    public Properties Config = new Properties();
    public FileInputStream fis;
    public Logger log = Logger.getLogger("devpinoyLogger");
    public WebDriverWait wait;
    public ExtentReports rep = ExtentManager.getInstance();
    public ExtentTest test;
    public String browser;

    public void addLog(String message) {
        log.debug("Thread : " + getThreadValue(dr.get()) + "  " + "Browser : " + browser + "  " + message);
    }

    public void captureScreenshot() {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

        try {
            FileUtils.copyFile(scrFile,
                    new File(System.getProperty("user.dir") + "\\reports\\" + screenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getExtTest().log(LogStatus.INFO, " Screenshot -> " + test.addScreenCapture(screenshotName));
    }

    public void setUp() {

        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") +
                        "\\src\\test\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Config.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                fis = new FileInputStream(System.getProperty("user.dir") +
                        "\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                OR.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public WebDriver getDriver() {
        return dr.get();
    }

    public void setWebDriver(RemoteWebDriver driver) {
        dr.set(driver);
    }

    public void setExtentTest(ExtentTest et) {
        exTest.set(et);
    }

    public ExtentTest getExtTest() {

        return exTest.get();
    }

    public String getThreadValue(Object value) {

        String text = value.toString();
        String[] nextText = text.split(" ");
        String text2 = nextText[nextText.length - 1].replace("(", "").replace(")", "");
        String[] newText2 = text2.split("-");
        String reqText = newText2[newText2.length - 1];
        return reqText;
    }

    public void openBrowser(String browser) throws MalformedURLException {

        this.browser = browser;
        DesiredCapabilities cap = null;

        if (browser.equals("firefox")) {
            cap = DesiredCapabilities.firefox();
            cap.setBrowserName("firefox");
            cap.setPlatform(Platform.ANY);

        } else if (browser.equals("chrome")) {
            cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            cap.setPlatform(Platform.ANY);

        } else if (browser.equals("chrome")) {
            cap = DesiredCapabilities.internetExplorer();
            cap.setBrowserName("iexplore");
            cap.setPlatform(Platform.WINDOWS);
        }

        driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), cap);
        setWebDriver(driver);
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        getExtTest().log(LogStatus.INFO, "Browser opened successfully " + browser);
        //System.out.println(dr.get());
        System.out.println("Thread value is : " + getThreadValue(dr.get()));
    }

    public void reportPass(String msg) {
        getExtTest().log(LogStatus.PASS, msg);
    }

    public void reportFailure(String msg) {
        getExtTest().log(LogStatus.FAIL, msg);
        captureScreenshot();
        Assert.fail(msg);
    }

    public void navigate(String url) {
        getDriver().get(Config.getProperty(url));
        getExtTest().log(LogStatus.INFO, "Navigating to " + Config.getProperty(url));
    }

    public void click(String locator) {
        try {
            if (locator.endsWith("_CSS")) {
                getDriver().findElement(By.cssSelector(OR.getProperty(locator))).click();
            } else if (locator.endsWith("_XPATH")) {
                getDriver().findElement(By.xpath(OR.getProperty(locator))).click();
            } else if (locator.endsWith("_ID")) {
                getDriver().findElement(By.id(OR.getProperty(locator))).click();
            }
            addLog("Clicking on an Element : " + locator);
        } catch (Throwable t) {
            reportFailure("Failing while clicking on an Element " + locator);
        }
    }

    public void type(String locator, String value) {

        try {
            if (locator.endsWith("_CSS")) {
                getDriver().findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
            } else if (locator.endsWith("_XPATH")) {
                getDriver().findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
            } else if (locator.endsWith("_ID")) {
                getDriver().findElement(By.id(OR.getProperty(locator))).sendKeys(value);
            }
            addLog("Typing in an Element : " + locator);
        } catch (Throwable t) {
            reportFailure("Failing while typing in an Element " + locator);
        }
    }

    public void select(String locator, String value) {

        try {
            if (locator.endsWith("_CSS")) {
                dropdown = getDriver().findElement(By.cssSelector(OR.getProperty(locator)));
            } else if (locator.endsWith("_XPATH")) {
                dropdown = getDriver().findElement(By.xpath(OR.getProperty(locator)));
            } else if (locator.endsWith("_ID")) {
                dropdown = getDriver().findElement(By.id(OR.getProperty(locator)));
            }
            Select select = new Select(dropdown);
            select.selectByVisibleText(value);
        } catch (Throwable t) {
            reportFailure("Failing while Selecting an Element " + locator);
        }
    }

    public boolean isElementPresent(By by) {
        try {
            getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
