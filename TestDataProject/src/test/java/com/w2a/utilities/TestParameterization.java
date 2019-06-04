package com.w2a.utilities;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestParameterization {

    @Test(dataProvider = "getData")
    public void testData(Hashtable<String, String> data) {

        //System.out.println(data.get("Runmode")+"---"+data.get("customer")+"---"+data.get("currency"));
        System.out.println(data.get("Runmode") + "---" + data.get("firstname"));
    }

    @DataProvider
    public Object[][] getData() {
        ExcelReader excel = new ExcelReader(
                System.getProperty("user.dir") +
                        "\\TestDataProject\\src\\test\\resources\\testdata\\BankManagerSuite.xlsx");
        int rows = excel.getRowCount(Constants.DATA_SHEET);
        System.out.println("Total rows are : " + rows);
        String testName = "AddCustomerTest";

        // Find the test case start row
        int testCaseRowNum = 1;
        for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {
            String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
            if (testCaseName.equalsIgnoreCase(testName))
                break;
        }
        System.out.println("Test case starts from row num: " + testCaseRowNum);

        // Checking total rows in test case
        int dataStartRowNum = testCaseRowNum + 2;
        int testRows = 0;
        while (!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum + testRows).equals("")) {
            testRows++;
        }
        System.out.println("Total rows of data are : " + testRows);

        // Checking total cols in test case
        int colStartColNum = testCaseRowNum + 1;
        int testCols = 0;
        while (!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")) {
            testCols++;
        }
        System.out.println("Total cols are : " + testCols);

        // Printing data
        Object[][] data = new Object[testRows][1];
        int i = 0;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
            Hashtable<String, String> table = new Hashtable<String, String>();
            for (int cNum = 0; cNum < testCols; cNum++) {

                //System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
                String testData = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
                String colName = excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);
                table.put(colName, testData);
            }
            data[i][0] = table;
            i++;
        }
        return data;
    }
}
