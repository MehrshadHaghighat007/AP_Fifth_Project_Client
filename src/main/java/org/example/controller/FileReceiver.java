package org.example.controller;

import org.example.model.Storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReceiver implements Runnable {
    private DatagramSocket serverSocketII;
    private DatagramPacket initialPacket;
    private String clientDirectory;

    public FileReceiver(DatagramSocket socket, DatagramPacket packet) {
        this.serverSocketII = socket;
        this.initialPacket = packet;
        this.clientDirectory = "/home/mehrshad/Downloads/Repository";
    }

    @Override
    public void run() {
        FileOutputStream fileOutputStream = null;
        try {
//            InetAddress clientAddress = initialPacket.getAddress();
//            int clientPort = initialPacket.getPort();
            String fileName = new String(initialPacket.getData(), 0, initialPacket.getLength());
//            System.out.println("Received request for file: " + fileName);

//            Files.createDirectories(Paths.get(clientDirectory));

            File file = new File(clientDirectory, fileName);

            fileOutputStream = new FileOutputStream(file);

            while (true) {
                byte[] receiveData = new byte[Storage.getPacketSize()];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocketII.receive(receivePacket);
                int len = receivePacket.getLength();

                if (len == 0) {
                    break;
                }

                new MainThread(new PacketReceiver(fileOutputStream, receivePacket)).run();
            }

            System.out.println("File received successfully : " + fileName);

//            String ackMessage = "File " + fileName + " received successfully";
//            new MainThread(new UDPSender(serverSocketII, clientAddress, clientPort, ackMessage.getBytes())).start();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
