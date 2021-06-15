package com.saucedemo;

import org.testng.annotations.*;

public class LoginLogoutTests extends TestBase {

    @Test
    public void testLoginLogout() throws Exception {
        app.logout();
    }

}
