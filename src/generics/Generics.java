package generics;


public class Generics {

    // 1. Generic class
    static class Box<T> {
        private T value;

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }
    }

    // 2. Generic method
    public static <T> void display(T data) {
        System.out.println(data);
    }

    // 3. Bounded generic method
    public static <T extends Number> void printNumber(T num) {
        System.out.println(num);
    }

    public static void main(String[] args) {

        // Box storing Integer
        Box<Integer> b1 = new Box<>();
        b1.setValue(100);
        System.out.println(b1.getValue());

        // Same class storing String
        Box<String> b2 = new Box<>();
        b2.setValue("Java");
        System.out.println(b2.getValue());

        // Generic method
        display(10);
        display("Hello");

        // Bounded method
        printNumber(25);
        printNumber(12.5);

        // printNumber("Java"); // Compile-time error
    }
}
