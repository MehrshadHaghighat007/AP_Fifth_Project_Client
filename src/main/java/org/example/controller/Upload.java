package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Upload {
    private DatagramSocket udpSocketII;
    private BufferedReader consoleReader;
    private String username;

    public Upload(DatagramSocket udpSocketII, BufferedReader consoleReader, String username) {
        this.udpSocketII = udpSocketII;
        this.consoleReader = consoleReader;
        this.username = username;
    }

    public void upload() throws IOException {
        new MainThread(new UDPSender(udpSocketII, Storage.getServerPortIi(), username.getBytes())).run();
        System.out.println(CLI.upload());
        String path = consoleReader.readLine();
        File file = new File(path);
        if (file.exists()) {
            Path findPath = Paths.get(path);
            String fileName = findPath.getFileName().toString();
            new MainThread(new UDPSender(udpSocketII, Storage.getServerPortIi(), fileName.getBytes())).run();
            byte[] permission = new byte[Storage.getPacketSize()];
            DatagramPacket datagramPacket = new DatagramPacket(permission, permission.length);
            udpSocketII.receive(datagramPacket);
            String permissionString = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            if (permissionString.equals("0")) {
                System.out.println(CLI.uploadWarning());
                upload();
            } else {
                new MainThread(new FileSender(udpSocketII, file)).run();
//                uploadOrDownload();
            }
        } else {
            System.out.println(CLI.pathWarning());
            upload();
        }
    }
}
