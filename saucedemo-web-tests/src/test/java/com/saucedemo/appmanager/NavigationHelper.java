package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {
    private WebDriver wd;

    public NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void goToCart() {
        wd.findElement(By.xpath("//div[@id='shopping_cart_container']/a/span")).click();
    }

    public void returnToProductPage() {
        wd.findElement(By.id("back-to-products")).click();
    }
}
