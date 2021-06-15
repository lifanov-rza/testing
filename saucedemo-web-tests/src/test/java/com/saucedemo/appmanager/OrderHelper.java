package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderHelper {
    private WebDriver wd;

    public OrderHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void addProduct(String s) {
        wd.findElement(By.id(s)).click();
    }

    public void confirmOrder() {
        wd.findElement(By.id("finish")).click();
    }

    public void goToCheckoutOverview() {
        wd.findElement(By.id("continue")).click();
    }

    public void checkoutCart() {
        wd.findElement(By.id("checkout")).click();
    }

    public void fillPostalCode(String postalcode) {
        wd.findElement(By.id("postal-code")).click();
        wd.findElement(By.id("postal-code")).clear();
        wd.findElement(By.id("postal-code")).sendKeys(postalcode);
    }

    public void fillLastName(String lastname) {
        wd.findElement(By.id("last-name")).click();
        wd.findElement(By.id("last-name")).clear();
        wd.findElement(By.id("last-name")).sendKeys(lastname);
    }

    public void fillFirstName(String firstname) {
        wd.findElement(By.id("first-name")).click();
        wd.findElement(By.id("first-name")).clear();
        wd.findElement(By.id("first-name")).sendKeys(firstname);
    }
}
