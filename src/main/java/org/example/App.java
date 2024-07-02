package org.example;

import org.example.controller.Authentication;
import org.example.controller.AuthenticationManager;
import org.example.model.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        try (Socket tcpSocket = new Socket(Storage.getServerAddress(), Storage.getServerPort());
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter tcpWriter = new PrintWriter(tcpSocket.getOutputStream(), true);
             BufferedReader tcpReader = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
             DatagramSocket udpSocket = new DatagramSocket()) {
            new Authentication(consoleReader, tcpWriter, tcpReader).signInAndSignUpManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
