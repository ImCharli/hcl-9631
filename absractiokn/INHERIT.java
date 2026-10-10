package absractiokn;

interface InterfaceA {
    void m1();
}

interface InterfaceB {
    void m2();
}

class ChildC implements InterfaceA, InterfaceB {

    @Override
    public void m1() {
        System.out.println("Method from A");
    }

    @Override
    public void m2() {
        System.out.println("Method from B");
    }

    public static void main(String[] args) {
        ChildC obj = new ChildC();
        obj.m1();
        obj.m2();
    }
}