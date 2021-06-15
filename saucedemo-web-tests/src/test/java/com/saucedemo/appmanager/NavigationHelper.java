package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToCart() {
        click(By.xpath("//div[@id='shopping_cart_container']/a/span"));
    }

    public void returnToProductPage() {
        click(By.id("back-to-products"));
    }
}
