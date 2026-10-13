package exception;


import java.io.*;

// 1. Custom checked exception
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// 2. Parent class for exception handling in overriding
class Parent {
    void display() throws IOException {
        System.out.println("Parent display()");
    }
}

class Child extends Parent {
    @Override
    void display() throws FileNotFoundException {
        System.out.println("Child display()");
    }
}

// 3. Bank account class
class BankAccount {
    private double balance = 5000;

    public void withdraw(double amount)
            throws InsufficientBalanceException {

        // Unchecked exception using throw
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive"
            );
        }

        // Custom checked exception using throw
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }

        balance -= amount;
        System.out.println("Withdrawal successful");
        System.out.println("Balance: " + balance);
    }
}

public class Exxeption {

    // 4. Checked exception declared using throws
    static void readFile() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader("data.txt"))) {

            System.out.println("File content: " + br.readLine());
        }
    }

    // 5. Exception propagation
    static void method3() {
        int result = 10 / 0;
    }

    static void method2() {
        method3();
    }

    static void method1() {
        method2();
    }

    public static void main(String[] args) {

        BankAccount account = new BankAccount();

        // 6. try, multiple catch, finally
        try {
            account.withdraw(6000);

        } catch (InsufficientBalanceException e) {
            System.out.println("Custom exception: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("General exception: " + e.getMessage());

        } finally {
            System.out.println("Withdrawal operation completed");
        }

        // 7. Unchecked exception handling
        try {
            int[] arr = {10, 20, 30};
            System.out.println(arr[5]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index");
        }

        // 8. NumberFormatException
        try {
            int number = Integer.parseInt("abc");

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }

        // 9. Exception propagation
        try {
            method1();

        } catch (ArithmeticException e) {
            System.out.println("Propagation: division by zero");
        }

        // 10. Checked exception and try-with-resources
        try {
            readFile();

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }

        // 11. Method overriding and exceptions
        Parent obj = new Child();

        try {
            obj.display();

        } catch (IOException e) {
            System.out.println("Display error");
        }

        // 12. NullPointerException
        try {
            String str = null;
            System.out.println(str.length());

        } catch (NullPointerException e) {
            System.out.println("Null reference detected");
        }

        System.out.println("Program continues normally");
    }
}
