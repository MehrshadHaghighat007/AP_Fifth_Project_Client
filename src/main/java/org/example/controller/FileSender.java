package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;

import java.io.*;
import java.net.*;

public class FileSender implements Runnable {
    private DatagramSocket clientSocket;
    private File file;

    public FileSender(DatagramSocket socket, File file) {
        this.clientSocket = socket;
        this.file = file;
    }

    @Override
    public void run() {
        FileInputStream fileInputStream = null;
        try {
            byte[] fileNameData = file.getName().getBytes();
            new MainThread(new UDPSender(clientSocket, fileNameData)).run();

            fileInputStream = new FileInputStream(file);
            byte[] sendData = new byte[Storage.getPacketSize()];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(sendData)) != -1) {
                byte[] packetData = new byte[bytesRead];
                System.arraycopy(sendData, 0, packetData, 0, bytesRead);
                new MainThread(new UDPSender(clientSocket, packetData)).run();
                sendData = new byte[Storage.getPacketSize()];
            }

            new MainThread(new UDPSender(clientSocket, new byte[0])).run();

            System.out.println(CLI.fileSent(file.getName()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

