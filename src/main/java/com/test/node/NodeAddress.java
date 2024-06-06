package com.test.node;

public class NodeAddress {
    private String host;
    private int port;

    public NodeAddress(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
