package com.w2a.pages;

import com.w2a.base.Page;

public class LoginPage extends Page {

    public ZohoAppPage doLogin(String username, String password) {

        type("email_CSS", username);
        type("password_CSS", password);
        click("signbtn_CSS");
        return new ZohoAppPage();
    }

    public void gotoSalesandMarketing() {
    }

    public void gotoFinance() {
    }
}
