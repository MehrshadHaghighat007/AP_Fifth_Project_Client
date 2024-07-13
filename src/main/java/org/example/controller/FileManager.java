package org.example.controller;

import org.example.model.Storage;
import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramSocket;

public class FileManager {
    private DatagramSocket udpSocketII;
    private DatagramSocket getUdpSocketIII;
    private BufferedReader consoleReader;
    private String username;

    public FileManager(DatagramSocket udpSocketII, DatagramSocket getUdpSocketIII, BufferedReader consoleReader, String username) {
        this.udpSocketII = udpSocketII;
        this.getUdpSocketIII = getUdpSocketIII;
        this.consoleReader = consoleReader;
        this.username = username;
    }

    public void uploadOrDownload() throws IOException {
        System.out.println(CLI.uploadAndDownload());
        String input = consoleReader.readLine();
        if (LogicManager.oneOrTwoChecking(input)) {
//            new MainThread(new UDPSender(udpSocketII, Storage.getServerPortIi(), input.getBytes())).run();
            if (input.equals("1")) {
                new Upload(udpSocketII, consoleReader, username).upload();
            } else {
                new Download(getUdpSocketIII, consoleReader, username).download();
            }
        } else {
            System.out.println(CLI.Warning());
            uploadOrDownload();
        }
    }
}

