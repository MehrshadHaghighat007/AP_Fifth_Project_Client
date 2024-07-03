package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPUploadAndDownloadHandler {
    private DatagramSocket udpSocket;
    private BufferedReader consoleReader;

    public UDPUploadAndDownloadHandler(DatagramSocket udpSocket, BufferedReader consoleReader) {
        this.udpSocket = udpSocket;
        this.consoleReader = consoleReader;
    }

    public void uploadAndDownloadManager() throws IOException {
        byte[] list = "0".getBytes();
        DatagramPacket listRequest = new DatagramPacket(list, list.length, InetAddress.getByName(Storage.getServerAddress()), Storage.getServerPort());
        udpSocket.send(listRequest);
        System.out.println(CLI.list());

    }
}
