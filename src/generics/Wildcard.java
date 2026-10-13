package generics;


import java.util.*;

public class Wildcard {

    // 1. Unbounded wildcard
    static void display(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // 2. Upper-bounded wildcard
    // Accepts Number or its subclasses
    static double sum(List<? extends Number> list) {
        double total = 0;

        for (Number n : list) {
            total += n.doubleValue();
        }

        return total;
    }

    // 3. Lower-bounded wildcard
    // Accepts Integer or a supertype of Integer
    static void addIntegers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
    }

    public static void main(String[] args) {

        // Unbounded wildcard
        List<String> names = Arrays.asList("Amit", "Rahul");
        List<Integer> numbers = Arrays.asList(10, 20, 30);

        display(names);
        display(numbers);

        // Upper-bounded wildcard
        List<Integer> marks = Arrays.asList(80, 90, 70);
        List<Double> prices = Arrays.asList(10.5, 20.5);

        System.out.println(sum(marks));
        System.out.println(sum(prices));

        // Lower-bounded wildcard
        List<Integer> a = new ArrayList<>();
        List<Number> b = new ArrayList<>();
        List<Object> c = new ArrayList<>();

        addIntegers(a);
        addIntegers(b);
        addIntegers(c);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
