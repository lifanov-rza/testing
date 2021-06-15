package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        wd.get("https://www.saucedemo.com/");
        type(By.id("user-name"), username);
        type(By.id("password"), password);
        click(By.id("login-button"));
    }

    public void logout() {
        click(By.id("react-burger-menu-btn"));
        click(By.id("logout_sidebar_link"));
    }


}
