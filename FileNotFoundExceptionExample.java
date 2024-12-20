package Assgt1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;

public class FileNotFoundExceptionExample {
    public static void main(String[] args) {
        try {
            // Attempt to open a file that doesn’t exist
            FileReader file = new FileReader("nonexistentfile.txt");
        } catch (FileNotFoundException e) {
            // Handle the exception
            System.out.println("FileNotFoundException caught: " + e.getMessage());
        }
    }
}



