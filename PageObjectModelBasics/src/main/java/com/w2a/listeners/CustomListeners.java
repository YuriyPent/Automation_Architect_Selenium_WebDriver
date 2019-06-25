package com.w2a.listeners;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.Page;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.Utilities;
import org.testng.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

    public String messageBody;

    public void onFinish(ITestContext arg0) {

    }

    public void onStart(ITestContext arg0) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

    }

    public void onTestFailure(ITestResult arg0) {

        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {
            Utilities.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.log(LogStatus.FAIL, arg0.getName().toUpperCase() + " Failed with exception : " + arg0.getThrowable());
        test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

        Reporter.log("Click to see Screenshot");
        Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
        Reporter.log("<br>");
        Reporter.log("<br>");
        Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName + " height=200 width=200></img></a>");
        rep.endTest(test);
        rep.flush();
    }

    public void onTestSkipped(ITestResult arg0) {

        test.log(LogStatus.SKIP, arg0.getName().toUpperCase() + " Skipped the test as the Run mode is NO");
        rep.endTest(test);
        rep.flush();
    }


    public void onTestStart(ITestResult arg0) {

        test = rep.startTest(arg0.getName().toUpperCase());
    }

    public void onTestSuccess(ITestResult arg0) {

        test.log(LogStatus.PASS, arg0.getName().toUpperCase() + " PASS");
        rep.endTest(test);
        rep.flush();
    }

    public void onFinish(ISuite arg0) {

        MonitoringMail mail = new MonitoringMail();

        try {
            messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
                    + ":8080/job/LiveProject%20-%20PageObjectModel/Extent_Report/";
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void onStart(ISuite arg0) {

    }
}
