package com.rao2100.starter.models;

public class SnmpConfig {

    private String community;
    private String trapOid;
    private String serverAddress;

    /**
     * serverAddress of the snmpd format is ip/port ie: 10.3.11.83/162
     * 162 is the default snmptrapd port
     */
    public SnmpConfig(String community, String trapOid, String serverAddress) {
        this.community = community;
        this.trapOid = trapOid;
        this.serverAddress = serverAddress;
    }

    public String getCommunity() {
        return community;
    }

    public String getTrapOid() {
        return trapOid;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public String getIpAddress() {
        return serverAddress.split("/")[0];
    }

}
