package org.example.controller;

import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramSocket;

public class Authentication {
    private BufferedReader consoleReader;
    private PrintWriter tcpWriter;
    private BufferedReader tcpReader;
    private DatagramSocket udpSocketI;
    private DatagramSocket udpSocketII;
    private DatagramSocket udpSocketIII;

    public Authentication(BufferedReader consoleReader, PrintWriter tcpWriter, BufferedReader tcpReader, DatagramSocket udpSocketI, DatagramSocket udpSocketII, DatagramSocket udpSocketIII) {
        this.consoleReader = consoleReader;
        this.tcpWriter = tcpWriter;
        this.tcpReader = tcpReader;
        this.udpSocketI = udpSocketI;
        this.udpSocketII = udpSocketII;
        this.udpSocketIII = udpSocketIII;
    }

    public void signInAndSignUpManager() throws IOException {
        System.out.println(CLI.signInAndSignUp());
        String input = consoleReader.readLine();
        if (LogicManager.oneOrTwoChecking(input)) {
            tcpWriter.println(input);
            usernameManager();
        } else {
            System.out.println(CLI.Warning());
            signInAndSignUpManager();
        }
    }

    private void usernameManager() throws IOException {
        System.out.println(CLI.username());
        String username = consoleReader.readLine();
        if (username != null) {
            tcpWriter.println(username);
        } else {
            System.out.println(CLI.usernameWarning());
            usernameManager();
        }
        String input = tcpReader.readLine();
        if (input.equals("0")) {
            passwordManager(username);
        } else if (input.equals("1")) {
            System.out.println(CLI.signInWrongUsername());
            usernameManager();
        } else if (input.equals("2")) {
            passwordManager(username);
        } else if (input.equals("3")) {
            System.out.println(CLI.signUpWrongUsername());
            usernameManager();
        }
    }

    private void passwordManager(String username) throws IOException {
        System.out.println(CLI.password());
        String password = consoleReader.readLine();
        if (password != null) {
            tcpWriter.println(password);
        } else {
            System.out.println(CLI.passwordWarning());
            passwordManager(username);
        }
        int input = Integer.parseInt(tcpReader.readLine());
        if (input == 0) {
            System.out.println(CLI.signIn(username));
            receiveAndSendFiles(username);
        } else if (input == 1) {
            System.out.println(CLI.incorrectPassword());
            passwordManager(username);
        } else if (input == 2) {
            System.out.println(CLI.signUp(username));
            receiveAndSendFiles(username);
        }
    }

    private void receiveAndSendFiles(String username) throws IOException {
        new ListOfTheFiles(udpSocketI, username).uploadAndDownloadManager();
        new FileManager(udpSocketII, udpSocketIII, consoleReader, username).uploadOrDownload();
    }
}
