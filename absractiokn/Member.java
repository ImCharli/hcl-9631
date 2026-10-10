package abstraction;

interface MyInterface {
    static void m1() {
        System.out.println("A");
    }
}

class MyClass implements MyInterface {
    static void m1() {
        System.out.println("B");
    }

    public static void main(String[] args) {
        MyInterface.m1();
        MyClass.m1();
    }
}