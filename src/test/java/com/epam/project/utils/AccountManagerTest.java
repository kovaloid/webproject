package com.epam.project.utils;

import com.epam.project.consts.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountManagerTest {

    @Test
    public void checkBeforeSignUp() throws Exception {
        assertEquals(AccountManager.checkBeforeSignUp("client", "client", "client"), Account.Result.FAIL_LOGIN);
        assertEquals(AccountManager.checkBeforeSignUp("aBcDeFgHiJkL", "1", "1"), Account.Result.FAIL_PASSWORD);
        assertEquals(AccountManager.checkBeforeSignUp("aBcDeFgHiJkL", "123456789", "1"), Account.Result.FAIL_REPEAT);
        assertEquals(AccountManager.checkBeforeSignUp("aBcDeFgHiJkL", "aBcDeFgHiJkL", "aBcDeFgHiJkL"), Account.Result.SUCCESS);
    }

    @Test
    public void checkBeforeAuth() throws Exception {
        assertEquals(AccountManager.checkBeforeAuth("aBcDeFgHiJkL", "admin"), Account.Result.FAIL_LOGIN);
        assertEquals(AccountManager.checkBeforeAuth("admin", "aBcDeFgHiJkL"), Account.Result.FAIL_PASSWORD);
        assertEquals(AccountManager.checkBeforeAuth("admin", "admin"), Account.Result.SUCCESS);
    }

    @Test
    public void defineRole() throws Exception {
        assertEquals(AccountManager.defineRole("admin"), Account.Role.ADMIN);
        assertEquals(AccountManager.defineRole("client"), Account.Role.CLIENT);
    }
}