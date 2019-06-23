package com.w2a.pages;

import com.w2a.base.Page;
import org.openqa.selenium.By;

public class HomePage extends Page {


    public void goToSupport() {
        driver.findElement(By.cssSelector(".signing>a:nth-child(2)")).click();
    }

    public void goToSignUp() {
        driver.findElement(By.cssSelector(".signup")).click();
    }

    public LoginPage goToLogin() {
        click("loginlink_CSS");
        return new LoginPage();
    }

    public void goToZohoEdu() {
    }

    public void goToLearnMore() {
    }


    public void validateFooterLinks() {
    }
}
