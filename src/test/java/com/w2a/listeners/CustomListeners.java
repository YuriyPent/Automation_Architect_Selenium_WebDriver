package com.w2a.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.TestUtil;
import org.testng.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {

    public String meaasgeBody;
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

        test.log(LogStatus.SKIP, iTestResult.getName().toUpperCase() + " Skipped the test as the Run mode is NO");
        rep.endTest(test);
        rep.flush();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

    public void onStart(ISuite iSuite) {

    }

    public void onFinish(ISuite iSuite) {

        MonitoringMail mail = new MonitoringMail();

        try {
            meaasgeBody = "http://"+ InetAddress.
                    getLocalHost().
                    getHostAddress()+ ":8080/job/DataDrivenLiveProject/Extent_20Reports/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, meaasgeBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
