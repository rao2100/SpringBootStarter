package com.rao2100.starter.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.logging.log4j.*;

public class SystemdJournalUtils {
    
    private static Logger logger = LogManager.getLogger(SystemdJournalUtils.class);
    
    public static void send() {
        ThreadContext.put("RAO_KEY", "rao value");
        ThreadContext.put("_SYSTEMD_UNIT", "another rao value");
        ThreadContext.put("SYSLOG_IDENTIFIER", "another rao value");
        logger.info("this is an example");
        
    }
    
    public static void run() {
        String s;
        Process p;
        try {
//            p = Runtime.getRuntime().exec("journalctl --output=json-pretty UNIT=firewalld.service");
//            p = Runtime.getRuntime().exec("journalctl --output=json-pretty UNIT=session-1.scope > /tmp/rao.txt");
//            p = Runtime.getRuntime().exec("journalctl -n 1 --output=json-pretty UNIT=session-1.scope");
//            p = Runtime.getRuntime().exec("journalctl -f --output=json-pretty --identifier=java");
            p = Runtime.getRuntime().exec("journalctl -f --output=json --unit=bluetooth.service");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null) {
//                System.out.println("line: " + s);
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JournalEntry je = mapper.readValue(s, JournalEntry.class);
//                    System.out.println("je: " + je.getUnit());
//                    System.out.println("je: " + je.getCursor());
                    System.out.println("je: " + je.getMessage());
                    if(je.getMessage().toLowerCase().contains("fail")){
                        System.out.println("Send failure SNMP trap");
                    } else if (je.getMessage().toLowerCase().contains("started")){
                        //check if its the failed unit
                        System.out.println("Send clear failed SNMP trap");
                    }
                    
                } catch (Exception e) {
                    logger.error("Parse error: " + e.getMessage());
                }
                
            }
            p.waitFor();
            System.out.println("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {
            System.out.println("e: " + e.getMessage());
        }
    }
    
}
