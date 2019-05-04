package com.w2a.testcases;

import com.w2a.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginAsBankManager() throws InterruptedException {

        driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
        Thread.sleep(3000);

    }
}
