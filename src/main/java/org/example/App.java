package org.example;

import org.example.controller.Authentication;
import org.example.controller.AuthenticationManager;
import org.example.controller.MainThread;
//import org.example.controller.UploadAndDownload;
import org.example.model.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try (Socket tcpSocket = new Socket(Storage.getServerAddress(), Storage.getServerPort());
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter consoleWriter = new PrintWriter(System.out);
             PrintWriter tcpWriter = new PrintWriter(tcpSocket.getOutputStream(), true);
             BufferedReader tcpReader = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
             DatagramSocket udpSocket = new DatagramSocket()) {
            new Authentication(consoleReader, tcpWriter, tcpReader).signInAndSignUpManager();
//            new MainThread(() -> {
//                while (true) {
//                    byte[] receiveData = new byte[Storage.getPacketSize()];
//                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//                    new MainThread(new UploadAndDownload(consoleReader, consoleWriter, udpSocket, receivePacket));
//                }
//
//            }).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
