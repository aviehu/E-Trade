package Tests.Acceptance.System;

import Domain.Stores.Store;
import Domain.Users.Users.Guest;
import Domain.Users.Users.Member;
import Domain.Users.Users.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    private User guest;
    private User member;
    private Store store;
    private String product;

    @Before
    public void setUp() throws Exception {
        guest = new Guest();
        member = new Guest().signIn("userName", "12345", 26,
                "username@gmail.com", "Tel-Aviv", "HaYarkon", 100, 24);
        member.logIn("userName", "12345");
        store = new Store("Store1", "Andulus", 123);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void purchaseCartTest(){

    }
}