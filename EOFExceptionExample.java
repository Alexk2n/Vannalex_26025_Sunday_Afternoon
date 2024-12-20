package Assgt1;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;




public class EOFExceptionExample {
    public static void main(String[] args) {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("examplefile.txt"))) {
            // Attempt to read beyond the file's content
            while (true) {
                dis.readByte();
            }
        } catch (EOFException e) {
            // Handle the exception
            System.out.println("EOFException caught: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
        }
    }
}


