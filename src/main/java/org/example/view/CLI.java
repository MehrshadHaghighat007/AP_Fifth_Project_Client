package org.example.view;

public class CLI {
    public static String signInAndSignUp() {
        return "Please choose : " + "\n" + "1 - Sign In" + "\n" + "2 - Sign Up";
    }

    public static String username() {
        return "username : ";
    }

    public static String password() {
        return "password : ";
    }

    public static String Warning() {
        return "Please enter a valid number.";
    }

    public static String incorrectPassword() {
        return "Incorrect password...";
    }

    public static String signInWrongUsername() {
        return "Username not found.";
    }

    public static String signUpWrongUsername() {
        return "The username is already registered in the system.";
    }

    public static String signIn(String username) {
        return "Successfully sign in as : " + username;
    }

    public static String signUp(String username) {
        return "Successfully sign up as : " + username;
    }

    public static String list() {
        return "List of the uploaded files : ";
    }

    public static String uploadAndDownload() {
        return "1 - Upload" + "\n" + "2 - Download";
    }

    public static String upload() {
        return "Enter path of the file that you want to upload : ";
    }

    public static String fileSent(String fileName) {
        return "File sent successfully : " + fileName;
    }

    public static String listHandler() {
        return "You do not upload any file yet.";
    }
}
