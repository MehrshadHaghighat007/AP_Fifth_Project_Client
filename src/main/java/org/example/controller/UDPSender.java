package org.example.controller;

import org.example.model.Storage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender implements Runnable {
    private DatagramSocket socket;
    private int port;
    private byte[] data;

    public UDPSender(DatagramSocket socket, int port, byte[] data) {
        this.socket = socket;
        this.port = port;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            if (data.length > 0) {
                for (int i = 0; i < data.length; i += Storage.getPacketSize()) {
                    int end = Math.min(data.length, i + Storage.getPacketSize());
                    byte[] chunk = new byte[end - i];

                    for (int j = 0; j < end - i; j++) {
                        chunk[j] = data[i + j];
                    }

                    DatagramPacket packet = new DatagramPacket(chunk, chunk.length, InetAddress.getByName(Storage.getServerAddress()), port);
                    socket.send(packet);
                }
            } else {
                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(Storage.getServerAddress()), port);
                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
