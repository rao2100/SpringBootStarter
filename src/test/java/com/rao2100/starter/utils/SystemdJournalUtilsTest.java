/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rao2100.starter.utils;

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
public class SystemdJournalUtilsTest {
    
    public SystemdJournalUtilsTest() {
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
     * Test of send method, of class SystemdJournalUtils.
     */
    @Test
    public void testSend() {
        System.out.println("send");
        SystemdJournalUtils.send();        
    }
    
    /**
     * Test of send method, of class SystemdJournalUtils.
     */
    @Test
    public void testRead() {
        System.out.println("read");
//        SystemdJournalUtils.read();
        
    }
    
}
