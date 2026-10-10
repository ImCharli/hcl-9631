package absractiokn;
abstract class A {
    abstract void m1();
    abstract void m2();
}

class B extends A {
    @Override
    void m1() {
        System.out.println("hello");
    }

    @Override
    void m2() {
        System.out.println("world");
    }
}

public class Abtractclass {
    public static void main(String[] args) {
        B b=new B();
        b.m1();
        b.m2();
    }
}