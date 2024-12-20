package Assgt1;

public class ArrayIndexOutOfBoundsExceptionExample {
    public static void main(String[] args) {
        try {
            // Attempt to access an invalid array index
            int[] array = new int[5];
            int value = array[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the exception
            System.out.println("ArrayIndexOutOfBoundsException caught: " + e.getMessage());
        }
    }
}

