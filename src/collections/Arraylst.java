package collections;

import java.util.ArrayList;

public class Arraylst {
    public static void main(String[] args) {

        // Initial capacity = 5, size = 0
        ArrayList<Integer> list = new ArrayList<>(5);

        System.out.println("Initial size: " + list.size()); // 0

        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("Size after adding 3 elements: " + list.size()); // 3

        list.add(40);
        list.add(50);
        list.add(60);

        System.out.println("Size after adding 6 elements: " + list.size()); // 6

        // ArrayList automatically increases capacity when required.
        // Its actual capacity is not directly accessible through the public API.
    }
}