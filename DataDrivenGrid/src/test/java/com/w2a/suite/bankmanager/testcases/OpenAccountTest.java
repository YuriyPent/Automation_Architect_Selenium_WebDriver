package com.w2a.suite.bankmanager.testcases;

import com.w2a.datadriven.base.TestBase;
import com.w2a.utilities.Constants;
import com.w2a.utilities.DataProviders;
import com.w2a.utilities.DataUtil;
import com.w2a.utilities.ExcelReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Hashtable;

public class OpenAccountTest extends TestBase {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "bankManagerDP")
    public void openAccountTest(Hashtable<String, String> data) throws MalformedURLException {

        super.setUp();
        test = rep.startTest("Open Account Test" + "   " + data.get("browser"));
        setExtentTest(test);
        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("BankManagerSuite", "OpenAccountTest", data.get("Runmode"), excel);
        openBrowser(data.get("browser"));
        navigate("testsiteurl");

        click("bmlBtn_CSS");
        click("openaccount_CSS");
        select("customer_CSS", data.get("customer"));
        select("currency_CSS", data.get("currency"));
        click("process_CSS");
        reportPass("Open Account test pass");
    }

    @AfterMethod
    public void tearDown() {
        if (rep != null) {
            rep.endTest(getExtTest());
            rep.flush();
        }
        getDriver().quit();
    }
}
