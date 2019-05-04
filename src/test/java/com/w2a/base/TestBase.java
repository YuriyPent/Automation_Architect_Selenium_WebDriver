package com.w2a.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;

public class TestBase {

    public static WebDriver driver;

    @BeforeSuite
    public void setUp(){

    }

    @AfterSuite
    public void tearDown() {

    }

}
