package Assgt1;

import java.io.*;
public class NullPointerExceptionExample {
         public static void main(String[] args) {
             try {
                 // Attempt to access a method on a null reference
                 String str = null;
                 str.length();
             } catch (NullPointerException e) {
                 // Handle the exception
                 System.out.println("NullPointerException caught: " + e.getMessage());
             }
         }




}




