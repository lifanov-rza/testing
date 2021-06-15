package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderHelper extends HelperBase {

    public OrderHelper(WebDriver wd) {
        super(wd);
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
        type(By.id("postal-code"), postalcode);
    }


    public void fillLastName(String lastname) {
        type(By.id("last-name"), lastname);
    }

    public void fillFirstName(String firstname) {
        type(By.id("first-name"), firstname);
    }
}
