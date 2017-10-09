/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rao2100.starter.utils;

import com.rao2100.starter.models.SnmpConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author openetdev
 */
public class TrapSenderTest {
    
    public TrapSenderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class TrapSender.
     */
    @Test
    public void testMain() {
        System.out.println("main");

        TrapSender snmp4JTrap = new TrapSender();
        SnmpConfig config = new SnmpConfig("public", ".1.3.6.1.2.1.1.6", "127.0.0.1/162");
//        System.out.println("config: " + config.getIpAddress());


        snmp4JTrap.sendSnmpV1Trap(config);
        snmp4JTrap.sendSnmpV2Trap(config);

    }

    
}
