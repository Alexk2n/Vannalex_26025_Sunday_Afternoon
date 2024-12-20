package Assgt1;
import java.lang.Integer;
public class ClassCastExceptionExample {
    public static void main(String[] args) {
        try {
            // Attempt an invalid type cast
            Object x = new Integer.valueOf(100);
            String y = (String) x;
        } catch (ClassCastException e) {
            // Handle the exception
            System.out.println("ClassCastException caught: " + e.getMessage());
        }
    }
}
