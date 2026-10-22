package springstart;
public class SingleMain {
    public static void main(String[] args) {
        SingleObject obj1 = SingleObject.createInstance();
        SingleObject obj2 = SingleObject.createInstance();
        SingleObject obj3 = SingleObject.createInstance();
        SingleObject obj4 = SingleObject.createInstance();
        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.equals(obj3));
        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
    }
}