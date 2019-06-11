package com.w2a.suite.customer.testcases;

import com.w2a.utilities.Constants;
import com.w2a.utilities.DataProviders;
import com.w2a.utilities.DataUtil;
import com.w2a.utilities.ExcelReader;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class OpenAccountTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "customerDP")
    public void openAccountTest(Hashtable<String, String> data) {

        ExcelReader excel = new ExcelReader(Constants.SUITE2_XL_PATH);
        DataUtil.checkExecution("CustomerSuite", "OpenAccountTest", data.get("Runmode"), excel);


    }


}
