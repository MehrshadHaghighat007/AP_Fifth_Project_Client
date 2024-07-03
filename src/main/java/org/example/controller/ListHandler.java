package org.example.controller;

import org.example.model.Storage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ListHandler implements Runnable {
    private DatagramSocket udpSocket;
    private DatagramPacket receivePacket;

    public ListHandler(DatagramSocket udpSocket, DatagramPacket receivePacket) {
        this.udpSocket = udpSocket;
        this.receivePacket = receivePacket;
    }
    @Override
    public void run() {
        String firstFile = new String(receivePacket.getData(), 0, receivePacket.getLength());
        while(true) {
            byte[] file = new byte[Storage.getPacketSize()];
            DatagramPacket re
        }

    }
}
