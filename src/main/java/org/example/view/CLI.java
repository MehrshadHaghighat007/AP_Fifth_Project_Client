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

    public static String pathWarning() {
        return "Please enter a valid path.";
    }

    public static String uploadWarning() {
        return "You have uploaded this file before.";
    }

    public static String download() {
        return "Please enter the name of the file you want to download : ";
    }

    public static String downloadWarning() {
        return "File not found.";
    }

    public static String usernameWarning() {
        return "Please enter a valid username.";
    }

    public static String passwordWarning() {
        return "Please enter a valid password.";
    }
}
