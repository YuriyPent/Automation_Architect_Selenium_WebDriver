package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BankManagerLoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException, IOException {

//        verifyEquals("abc", "xyz");

        log.debug("Inside Login Test");
        click("bmlBtn_CSS");
//        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
//        Thread.sleep(3000);
        Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))), "Login not successful");
        log.debug("Login successfully executed");


    }


}
