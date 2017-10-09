/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rao2100.starter.utils;

import com.rao2100.starter.models.SnmpConfig;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.util.Date;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.TimeTicks;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class TrapSender {

    private static final int DEFAULT_RETRIES = 2;
    private static final int DEFAULT_TIMEOUT = 5000;
    private static final long DEFAULT_SYSUPTIME = 111111;

    public TrapSender() {
    }

    /**
     * This methods sends the V1 trap to the Localhost in port 163
     */
    public void sendSnmpV1Trap(SnmpConfig config) {
        try {
            // Create Transport Mapping
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();

            // Create Target
            CommunityTarget comtarget = getCommunityTarget(SnmpConstants.version1, config.getCommunity(), config.getServerAddress());

            // Create PDU for V1
            PDUv1 pdu = new PDUv1();
            pdu.setType(PDU.V1TRAP);
            pdu.setEnterprise(new OID(config.getTrapOid()));
//            pdu.setGenericTrap(PDUv1.ENTERPRISE_SPECIFIC);
            pdu.setGenericTrap(PDUv1.COLDSTART);
            pdu.setSpecificTrap(1);
            pdu.setAgentAddress(new IpAddress(config.getIpAddress()));
            pdu.setTimestamp(DEFAULT_SYSUPTIME);
//            pdu.add(new VariableBinding(SnmpConstants.sysDescr, new OctetString("Weaver VNFM")));

            // Send the PDU
            Snmp snmp = new Snmp(transport);
            System.out.println("Sending V1 Trap to " + config.getServerAddress());
            snmp.send(pdu, comtarget);
            snmp.close();
        } catch (Exception e) {
            System.err.println("Error in Sending V1 Trap to " + config.getServerAddress());
            System.err.println("Exception Message = " + e.getMessage());
        }
    }

    /**
     * This methods sends the V2 trap to the Localhost in port 163
     */
    public void sendSnmpV2Trap(SnmpConfig config) {
        try {
            // Create Transport Mapping
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();

            // Create Target
            CommunityTarget comtarget = getCommunityTarget(SnmpConstants.version2c, config.getCommunity(), config.getServerAddress());

            // Create PDU for V2
            PDU pdu = new PDU();             
            pdu.add(new VariableBinding(SnmpConstants.sysUpTime, new TimeTicks(DEFAULT_SYSUPTIME)));
            pdu.add(new VariableBinding(SnmpConstants.snmpTrapOID, new OID(config.getTrapOid())));
            pdu.add(new VariableBinding(SnmpConstants.snmpTrapAddress, new IpAddress(config.getIpAddress())));

            // variable binding for Enterprise Specific objects, Severity (should be defined in MIB file)
            pdu.add(new VariableBinding(new OID(config.getTrapOid()), new OctetString("Major")));
            pdu.setType(PDU.NOTIFICATION);

            // Send the PDU
            Snmp snmp = new Snmp(transport);
            System.out.println("Sending V2 Trap to " + config.getServerAddress());
            snmp.send(pdu, comtarget);
            snmp.close();
        } catch (Exception e) {
            System.err.println("Error in Sending V2 Trap to " + config.getServerAddress());
            System.err.println("Exception Message = " + e.getMessage());
        }
    }

    private CommunityTarget getCommunityTarget(int version, String community, String serverAddress) {

        CommunityTarget comtarget = new CommunityTarget();
        comtarget.setVersion(version);
        comtarget.setCommunity(new OctetString(community));
        comtarget.setAddress(new UdpAddress(serverAddress));
        comtarget.setRetries(DEFAULT_RETRIES);
        comtarget.setTimeout(DEFAULT_TIMEOUT);

        return comtarget;
    }

}
