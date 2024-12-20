package Assgt1;

public class NumberFormatExceptionExample {
    public static void main(String[] args) {
        try {
            // Attempt to convert a string to a number when the format is invalid
            int number = Integer.parseInt("invalid number");
        } catch (NumberFormatException e) {
            // Handle the exception
            System.out.println("NumberFormatException caught: " + e.getMessage());
        }
    }
}
