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

public class AddCustomerTest extends TestBase {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "bankManagerDP")

    public void addCustomerTest(Hashtable<String, String> data) throws MalformedURLException {

        super.setUp();
        test = rep.startTest("Add Customer Test" + "   " + data.get("browser"));
        setExtentTest(test);
        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
        DataUtil.checkExecution("BankManagerSuite", "AddCustomerTest", data.get("Runmode"), excel);
        openBrowser(data.get("browser"));
        navigate("testsiteurl");

        click("bmlBtn_CSS");
        click("addCustBtn_CSS");
        type("firstname_CSS", data.get("firstname"));
        type("lastname_XPATH", data.get("lastname"));
        type("postcode_CSS", data.get("postcode"));
        click("addbtn_CSS");
        reportPass("Add customer test pass");
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
