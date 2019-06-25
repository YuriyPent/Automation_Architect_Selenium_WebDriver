package com.w2a.testcases;

import com.w2a.base.Page;
import org.testng.annotations.AfterSuite;

public class BaseTest {

    @AfterSuite
    public void tearDown() {

        Page.quit();
    }
}
