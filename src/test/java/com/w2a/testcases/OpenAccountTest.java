package com.w2a.testcases;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class OpenAccountTest extends TestBase {

    @Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
    public void openAccountTest(Hashtable<String, String> data) throws InterruptedException {

        if (!(TestUtil.isTestRunnable("openAccountTest", excel))) {

            throw new SkipException("Skipping the test " + "openAccountTest ".toUpperCase() + " as the Run mode is NO");
        }

        click("openaccount_CSS");
        select("customer_CSS", data.get("customer"));
        select("currency_CSS", data.get("currency"));
        click("process_CSS");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

    }


}
