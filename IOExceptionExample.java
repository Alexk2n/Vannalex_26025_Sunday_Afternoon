package Assgt1;

import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class IOExceptionExample {
    public static void main(String[] args) {
        try {
            // Attempt to read a non-existent file
            FileReader file = new FileReader("nonexistentfile.txt");
        } catch (IOException e) {
            // Handle the exception
            System.out.println("IOException caught: " + e.getMessage());
        }
    }
}


