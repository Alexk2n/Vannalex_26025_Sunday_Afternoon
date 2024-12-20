package Assgt1;

class ArithmeticExceptionExample {
        public static void main(String[] args) {
            try {
                // Attempt a division by zero
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                // Handle the exception
                System.out.println("ArithmeticException caught: Not Possible to divide any number by 0");
            }
        }
    }



