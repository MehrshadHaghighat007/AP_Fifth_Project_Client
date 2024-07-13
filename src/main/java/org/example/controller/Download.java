package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Download {
    private DatagramSocket udpSocketIII;
    private BufferedReader consoleReader;
    private String username;

    public Download(DatagramSocket udpSocketIII, BufferedReader consoleReader, String username) {
        this.udpSocketIII = udpSocketIII;
        this.consoleReader = consoleReader;
        this.username = username;
    }

    public void download() throws IOException {
        new MainThread(new UDPSender(udpSocketIII, Storage.getServerPortIi(), username.getBytes())).run();
        System.out.println(CLI.download());
        String fileName = consoleReader.readLine();
        new MainThread(new UDPSender(udpSocketIII, Storage.getServerPortIi(), fileName.getBytes())).run();
        byte[] existenceByteArray = new byte[Storage.getPacketSize()];
        DatagramPacket existencePacket = new DatagramPacket(existenceByteArray, existenceByteArray.length);
        udpSocketIII.receive(existencePacket);
        String existence = new String(existencePacket.getData(), 0, existencePacket.getLength());
        if (existence.equals("0")) {
            System.out.println(CLI.downloadWarning());
            download();
        } else {
            byte[] fileNameByteArray = new byte[Storage.getPacketSize()];
            DatagramPacket fileNamePacket = new DatagramPacket(fileNameByteArray, fileNameByteArray.length);
            udpSocketIII.receive(fileNamePacket);
            new MainThread(new FileReceiver(udpSocketIII, fileNamePacket)).run();
//            uploadOrDownload();
        }
    }
}
