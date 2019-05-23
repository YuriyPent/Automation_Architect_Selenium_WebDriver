package com.w2a.rough;

import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;

import javax.mail.MessagingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHostAdd {

    public static void main(String[] args) throws UnknownHostException, MessagingException {

        MonitoringMail mail = new MonitoringMail();
        String meaasgeBody =
                "http://" + InetAddress.
                        getLocalHost().
                        getHostAddress() + ":8080/job/DataDrivenLiveProject/Extent_20Reports/";
        mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, meaasgeBody);
    }
}
