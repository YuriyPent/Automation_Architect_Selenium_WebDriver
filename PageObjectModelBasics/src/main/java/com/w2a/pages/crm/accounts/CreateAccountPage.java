package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

public class CreateAccountPage extends Page {

    public void createAccount(String accountName) {
        type("accountname_CSS", accountName);
    }
}
