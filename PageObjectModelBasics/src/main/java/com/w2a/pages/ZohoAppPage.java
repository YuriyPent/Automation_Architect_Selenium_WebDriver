package com.w2a.pages;

import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;
import org.openqa.selenium.By;

public class ZohoAppPage extends Page {

    public void gotoChat() {
        driver.findElement(By.cssSelector(".zicon-apps-chat.zicon-apps-96")).click();
    }

    public CRMHomePage gotoCRM() {
        click("crmlink_CSS");
        return new CRMHomePage();
    }

    public void gotoSalesIQ() {
        driver.findElement(By.cssSelector(".zicon-apps-salesiq.zicon-apps-96")).click();
    }
}
