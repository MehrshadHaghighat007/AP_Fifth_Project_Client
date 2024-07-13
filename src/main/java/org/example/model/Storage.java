package org.example.model;


public class Storage {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT_I = 9876;
    private static final int SERVER_PORT_II = 9877;
    private static final int SERVER_PORT_III = 9878;
    private static final int PACKET_SIZE = 1024 * 1024;

    public static String getServerAddress() {
        return SERVER_ADDRESS;
    }

    public static int getServerPortI() {
        return SERVER_PORT_I;
    }

    public static int getServerPortIi() {
        return SERVER_PORT_II;
    }

    public static int getServerPortIii() {
        return SERVER_PORT_III;
    }

    public static int getPacketSize() {
        return PACKET_SIZE;
    }
}
