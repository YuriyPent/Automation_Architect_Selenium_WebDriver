package com.w2a.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class CustomListeners extends TestBase implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {

        test = rep.startTest(iTestResult.getName().toUpperCase());

    }

    public void onTestSuccess(ITestResult iTestResult) {

        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestBase.test.log(LogStatus.PASS, iTestResult.getName().toUpperCase() + " PASS");
        TestBase.test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
        Reporter.log("Click to see Screenshot");
//        Reporter.log("Login successfully executed");
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">" +
                "<img src=" + TestUtil.screenshotName + " height=200 width=200></img></a>");
        rep.endTest(test);
        rep.flush();

    }

    public void onTestFailure(ITestResult iTestResult) {

        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            TestUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestBase.test.log(LogStatus.FAIL, iTestResult.getName().toUpperCase() +
                " Failed with exception: " + iTestResult.getThrowable());
        TestBase.test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));



        Reporter.log("Click to see Screenshot");
//        Reporter.log("Login successfully executed");
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName +
                "><img src=" + TestUtil.screenshotName + " height=200 width=200></img></a>");
        rep.endTest(test);
        rep.flush();

    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
