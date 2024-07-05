package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ListOfTheFiles {
    private DatagramSocket udpSocket;
    private BufferedReader consoleReader;

    public ListOfTheFiles(DatagramSocket udpSocket, BufferedReader consoleReader) {
        this.udpSocket = udpSocket;
        this.consoleReader = consoleReader;
    }

    public void uploadAndDownloadManager() throws IOException {
        byte[] list = "0".getBytes();
        new MainThread(new UDPSender(udpSocket, list)).run();
        System.out.println(CLI.list());
        byte[] check = new byte[Storage.getPacketSize()];
        DatagramPacket datagramPacket = new DatagramPacket(check, check.length);
        udpSocket.receive(datagramPacket);
        String input = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        if (!input.equals("0")) {
            while (true) {
                new MainThread(new ListHandler(udpSocket, input));
            }
        }
        uploadOrDownload();
    }

    private void uploadOrDownload() throws IOException {
        System.out.println();
        System.out.println(CLI.uploadAndDownload());
        String input = consoleReader.readLine();
        if (LogicManager.oneOrTwoChecking(input)) {
            new MainThread(new UDPSender(udpSocket, input.getBytes())).run();
            byte[] reach = new byte[Storage.getPacketSize()];
            DatagramPacket datagramPacket = new DatagramPacket(reach, reach.length);
            udpSocket.receive(datagramPacket);
            String string = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
            if (string.equals("0")) {
                if (input.equals("1")) {
                    upload();
                } else {
                    download();
                }
            } else {
                System.out.println(CLI.Warning());
                uploadOrDownload();
            }
        }
    }

    private void upload() throws IOException {
        System.out.println(CLI.upload());
        String path = consoleReader.readLine();
        File file = new File(path);
        new MainThread(new FileSender(udpSocket, file)).run();
    }

    private void download() {

    }
}
