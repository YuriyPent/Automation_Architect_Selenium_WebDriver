package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddCustomerTest extends TestBase {

    @Test(dataProvider = "getData")
    public void addCustomer(String firstName, String lastName, String postCode, String alerttext) throws InterruptedException {

        click("addCustBtn_CSS");
        type("firstname_CSS", firstName);
        type("lastname_CSS", lastName);
        type("postcode_CSS", postCode);
        click("addbtn_CSS");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().contains(alerttext));
        alert.accept();
    }

    @DataProvider
    public Object[][] getData() {

        String sheetName = "AddCustomerTest";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);
        Object[][] data = new Object[rows - 1][cols];
//        Hashtable<String,String> table = null;

        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
//            table = new Hashtable<String,String>();
            for (int colNum = 0; colNum < cols; colNum++) {
                // data[0][0]
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//                table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
//                data[rowNum - 2][0] = table;
            }
        }
        return data;
    }
}
