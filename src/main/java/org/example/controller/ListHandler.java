package org.example.controller;

import org.example.model.Storage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ListHandler implements Runnable {
    private DatagramSocket udpSocket;
    private String input;

    public ListHandler(DatagramSocket udpSocket, String input) {
        this.udpSocket = udpSocket;
        this.input = input;
    }

    @Override
    public void run() {
        printMessage(input);
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
            printMessage(fileName);
        }
    }

    private void printMessage(String message) {
        synchronized (System.out) {
            System.out.println(message);
        }
    }
}
