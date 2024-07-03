package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ListOfTheFiles {
    private DatagramSocket udpSocket;
    private BufferedReader consoleReader;
    private

    public ListOfTheFiles(DatagramSocket udpSocket, BufferedReader consoleReader) {
        this.udpSocket = udpSocket;
        this.consoleReader = consoleReader;
    }

    public void uploadAndDownloadManager() throws IOException {
        byte[] list = "0".getBytes();
        new MainThread(new UDPSender(udpSocket.getInetAddress(), udpSocket.getPort(), list)).run();
        System.out.println(CLI.list());
        while (true) {
            byte[] listOfFiles = new byte[Storage.getPacketSize()];
            DatagramPacket receivePacket = new DatagramPacket(listOfFiles, listOfFiles.length);
            udpSocket.receive(receivePacket);
            new MainThread(new ListHandler(udpSocket, receivePacket));
        }

    }
}
