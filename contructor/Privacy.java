package contructor;
//contructor overloading
class Animal {

    String name = "Animal";

    Animal() {
        System.out.println("Animal constructor");
    }

    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {

    String name = "Dog";

    Dog() {
        super();   // calls Animal constructor
        System.out.println("Dog constructor");
    }

    @Override
    void sound() {
        System.out.println("Dog barks");
    }

    void show() {
        System.out.println(this.name);   // Dog
        System.out.println(super.name);  // Animal

        this.sound();                    // Dog's sound()
        super.sound();                   // Animal's sound()
    }
}

public class Privacy {
    public static void main(String[] args) {

        Dog d = new Dog();

        d.show();
    }
}