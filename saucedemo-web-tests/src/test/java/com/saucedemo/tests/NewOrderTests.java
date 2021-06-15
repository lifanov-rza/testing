package com.saucedemo.tests;

import org.testng.annotations.Test;

public class NewOrderTests extends TestBase {

    @Test
    public void testNewOrder() throws Exception {
        app.getOrderHelper().addProduct("add-to-cart-sauce-labs-backpack");
        app.getOrderHelper().addProduct("add-to-cart-sauce-labs-bike-light");
        app.getNavigationHelper().goToCart();
        app.getOrderHelper().checkoutCart();
        app.getOrderHelper().fillFirstName("qwerty");
        app.getOrderHelper().fillLastName("qazwsx");
        app.getOrderHelper().fillPostalCode("123456");
        app.getOrderHelper().goToCheckoutOverview();
        app.getOrderHelper().confirmOrder();
        app.getNavigationHelper().returnToProductPage();
    }

}
