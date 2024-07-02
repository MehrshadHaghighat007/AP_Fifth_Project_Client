package org.example.controller;

import org.example.view.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Authentication {
    private BufferedReader consoleReader;
    private PrintWriter tcpWriter;
    private BufferedReader tcpReader;

    public Authentication(BufferedReader consoleReader, PrintWriter tcpWriter, BufferedReader tcpReader) {
        this.consoleReader = consoleReader;
        this.tcpWriter = tcpWriter;
        this.tcpReader = tcpReader;
    }

    public void signInAndSignUpManager() throws IOException {
        System.out.println(CLI.signInAndSignUp());
        String input = consoleReader.readLine();
        if (AuthenticationManager.signInAndSignUpChecking(input)) {
            tcpWriter.println(input);
            usernameManager();
        } else {
            System.out.println(CLI.signInAndSignUpWarning());
            signInAndSignUpManager();
        }
    }

    private void usernameManager() throws IOException {
        System.out.println(CLI.username());
        String username = consoleReader.readLine();
        tcpWriter.println(username);
        int input = Integer.parseInt(tcpReader.readLine());
        if (input == 0) {
            passwordManager(username);
        } else if (input == 1) {
            System.out.println(CLI.signInWrongUsername());
            usernameManager();
        } else if (input == 2) {
            passwordManager(username);
        } else if (input == 3) {
            System.out.println(CLI.signUpWrongUsername());
            usernameManager();
        }
    }

    private void passwordManager(String username) throws IOException {
        System.out.println(CLI.password());
        tcpWriter.println(consoleReader.readLine());
        int input = Integer.parseInt(tcpReader.readLine());
        if (input == 0) {
            System.out.println(CLI.signIn(username));
        } else if (input == 1) {
            System.out.println(CLI.incorrectPassword());
            passwordManager(username);
        } else  if (input == 2) {
            System.out.println(CLI.signUp(username));
        }
    }
}
