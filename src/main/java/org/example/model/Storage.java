package org.example.model;



public class Storage {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080 ;
    private static final int PACKET_SIZE = 1024;

    public static String getServerAddress() {
        return SERVER_ADDRESS;
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }

    public static int getPacketSize() {
        return PACKET_SIZE;
    }
}
