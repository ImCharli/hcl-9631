package springstart;

public class SingleObject {
    //make constructor private
    private SingleObject(){
        System.out.println("Single Class");
    }

    public void display() {
        System.out.println("HELLO");
    }

    static final SingleObject obj = new SingleObject();
    public static SingleObject createInstance(){
        return obj;
    }

    public static void main(String[] args) {
        SingleObject obj2 = new SingleObject();
        System.out.println();
    }
}