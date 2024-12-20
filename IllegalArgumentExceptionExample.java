package Assgt1;

public class IllegalArgumentExceptionExample {
         public static void main(String[] args) {
             try {
                 // Pass an invalid argument to a method
                 Thread.sleep(-1000);
             } catch (IllegalArgumentException e) {
                 // Handle the exception
                 System.out.println("IllegalArgumentException caught: " + e.getMessage());
             } catch (InterruptedException e) {
                 System.out.println("InterruptedException caught: " + e.getMessage());
             }
         }
     }
