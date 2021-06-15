package com.saucedemo;

import org.testng.annotations.Test;

public class NewOrderTests extends TestBase {

    @Test
    public void testNewOrder() throws Exception {
        app.addProduct("add-to-cart-sauce-labs-backpack");
        app.addProduct("add-to-cart-sauce-labs-bike-light");
        app.goToCart();
        app.checkoutCart();
        app.fillFirstName("qwerty");
        app.fillLastName("qazwsx");
        app.fillPostalCode("123456");
        app.goToCheckoutOverview();
        app.confirmOrder();
        app.returnToProductPage();
    }

}
