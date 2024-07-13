package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;


import java.io.IOException;
import java.net.DatagramSocket;

public class ListOfTheFiles {
    private DatagramSocket udpSocketI;
    private String username;


    public ListOfTheFiles(DatagramSocket udpSocketI, String username) {
        this.udpSocketI = udpSocketI;
        this.username = username;
    }

    public void uploadAndDownloadManager() {
        byte[] list = username.getBytes();
        new MainThread(new UDPSender(udpSocketI, Storage.getServerPortI(), list)).run();
        System.out.println(CLI.list());
        new MainThread(new ListHandler(udpSocketI)).run();
    }
}
