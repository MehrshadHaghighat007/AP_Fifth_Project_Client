package org.example.controller;

import org.example.model.Storage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ListHandler implements Runnable {
    private DatagramSocket udpSocket;

    public ListHandler(DatagramSocket udpSocket) {
        this.udpSocket = udpSocket;
    }

    @Override
    public void run() {
        while (true) {
            byte[] fileNameByteArray = new byte[Storage.getPacketSize()];
            DatagramPacket receivePacket = new DatagramPacket(fileNameByteArray, fileNameByteArray.length);
            try {
                udpSocket.receive(receivePacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int len = receivePacket.getLength();
            if (len == 0) {
                break;
            }
            String fileName = new String(receivePacket.getData(), 0, len);
            System.out.println(fileName);
        }
    }
}
