package com.workshop.ETrade.Tests.Acceptance.System;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class SupplyTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
    }

    @After
    public void tearDown() throws Exception {
    }
}