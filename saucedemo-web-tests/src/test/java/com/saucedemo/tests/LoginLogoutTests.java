package com.saucedemo.tests;

import org.testng.annotations.*;

public class LoginLogoutTests extends TestBase {

    @Test
    public void testLoginLogout() throws Exception {
        app.getSessionHelper().logout();
    }

}
