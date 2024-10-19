// While you cannot have multiple main methods with the same signature, you can have overloaded versions of main in the same class.
public class OverloadedMain {
    // Standard main method
    public static void main(String[] args) {
        System.out.println("Standard main method.");
        // Call the overloaded main method
        main(10);  // Calls the overloaded main method
    }

    // Overloaded main method
    public static void main(int num) {
        System.out.println("Overloaded main method with integer: " + num);
    }
}


// Some new lerning
// In java Class can be declared as public, default or abstract/final/ static [it can be only for inner class]
// Default : Visible to all the classes within the same package, but not to classes in other packages.
// Protected: All classes in the same package (like default access)  and Subclasses, even if they are in different packages (through inheritance).

// Directly calling the parent class’s method (Vehicle's stop()) from within the subclass without using super is not possible in Java.
abstract class Vehicle {
    // Abstract method
    abstract void start();

    // Concrete method to stop the vehicle
    public void stop() {
        System.out.println("Vehicle is stopping...");
    }
}

// Concrete class Car that inherits from Vehicle and overrides stop()
class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting...");
    }

    // Overriding the stop() method
    @Override
    public void stop() {
        // Call the parent class stop() method
        super.stop();
        // Add more functionality (additional behavior)
        System.out.println("Car has completely stopped and is now parked.");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        myCar.start();  // Output: Car is starting...
        myCar.stop();   // Output: Vehicle is stopping...
        //         Car has completely stopped and is now parked.
    }
}


// Key points for static
//you can declare nested (inner) classes as static, but top-level classes cannot be static.

// You cannot call a default method directly from the interface without implementing the interface.
//Static methods belong to the interface itself and cannot be overridden.
//Default methods provide a default implementation but can be overridden by the implementing class.
interface MyInterface {
    default void display() {
        System.out.println("Default method in MyInterface");
    }

    static void staticMethod() {
        System.out.println("Static method in interface");
    }
}

public class Main {
    public static void main(String[] args) {
        // Using an anonymous class to call the default method
        MyInterface obj = new MyInterface() {};
        obj.display();  // Output: Default method in MyInterface

        MyInterface.staticMethod();  // Calling static method from interface
    }
}

//Method overriding applies only to methods, not to fields (data members).
class Parent {
    int num = 10;  // Data member in the parent class
}

class Child extends Parent {
    int num = 20;  // Data member in the child class (hides the parent class field)
}

public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        Parent parentReferenceToChild = new Child();  // Parent reference pointing to Child object

        System.out.println("Parent num: " + parent.num);  // Outputs 10
        System.out.println("Child num: " + child.num);  // Outputs 20
        System.out.println("Parent reference to Child num: " + parentReferenceToChild.num);  // Outputs 10 // field access is determined by the reference type
    }
}


// Case 2 [most asked in interview] it solves the dimaond problem
interface InterfaceA {
    default void display() {
        System.out.println("Default method in InterfaceA");
    }
}

interface InterfaceB {
    default void display() {
        System.out.println("Default method in InterfaceB");
    }
}

class MyClass implements InterfaceA, InterfaceB {
    @Override
    public void display() {
        // Call the default method of InterfaceA
        InterfaceA.super.display();

        // Call the default method of InterfaceB
        InterfaceB.super.display();
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.display();
    }
}

package test;
interface A {
    void dance();
}

interface B {
    void dance1();
}

public class Main implements B {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // If we are implementing ussing the annomyous class them do not use "implements A,B"
        A a = new A() {
            @Override
            public void dance() {
                System.out.println("tetstonhshd");
            }
        };
        a.dance();
    }
    
    // IF WE PUT THE implemnt then override of methods are must.    
    @Override
    public void dance1() {

    }
}

abstract class Animal {
    // Concrete method with implementation
    public void sleep() {
        System.out.println("Sleeping...");
    }
    
    // Abstract method without implementation
    abstract void sound();
}

**************interface concept********************
interface ParentInterface {
    void parentMethod();
}

interface ChildInterface extends ParentInterface {
    void childMethod();
}

class ImplementingClass implements ChildInterface {
    @Override
    public void parentMethod() {
        System.out.println("Parent method implementation.");
    }

    @Override
    public void childMethod() {
        System.out.println("Child method implementation.");
    }
}

public class Main {
    public static void main(String[] args) {
        ParentInterface parentRef = new ImplementingClass();

        // This is valid, as parentMethod is defined in ParentInterface
        parentRef.parentMethod();

        // This will cause a compile-time error, as childMethod is not in ParentInterface
        // parentRef.childMethod(); // Uncommenting this line will cause an error
    }
}

******************************
abstract class A {}
abstract class B {}


class MyClass extends A, B {}
// Error: Cannot extend more than one class, Abstract classes only support single inheritance

//Interfaces support multiple inheritance
//Interfaces: All fields are public, static, and final, meaning they are constants.
//Abstract Classes: Can have instance variables, and the fields can have any access modifier. Fields can be mutable.

//Interfaces do not support constructors.
//Abstract classes can have constructors, allowing subclasses to call them for shared initialization.
// An abstract method cannot exist in a completely non-abstract class unless that class itself is declared abstract.
class AbstractAnimal {
    abstract void makeSound(); // THIS IS WRONG
}

abstract class Animal {
    Animal() {
        System.out.println("Animal constructor");
    }
}

class Cat extends Animal {
    Cat() {
        System.out.println("Cat constructor");
    }
}

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        // Output:
        // Animal constructor
        // Cat constructor
    }
}
// Explanation: When you create an instance of Cat, the constructor of the Animal abstract class is called first, followed by the constructor of the Cat class.
******************************
//4. What happens if the method signature differs slightly (e.g., different parameters)?

class Parent {
    public void display(String message) {
        System.out.println("Parent: " + message);
    }
}

class Child extends Parent {
    // This is method overloading, not overriding
    public void display(int number) {
        System.out.println("Child: " + number);
    }
}

public class Main {
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.display("Hello");  // Output: Parent: Hello
        // obj.display(10);    // Compile-time error, as Parent class doesn't have display(int)
    }
}
*********************
// 3. Can you override a static method?
package test;
class Parent {
    public void display() {
        System.out.println("Parent static display()");
    }

    public static void display1() {
        System.out.println("Parent test static display()");
    }
}

class Child extends Parent {
    public void display() {
        System.out.println("Child static display()");
    }

    public static void display1() {
        System.out.println("Clilc test static display()");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent obj = new Child();
        obj.display();

        Child obj2 = new Child();
        obj2.display();

        Parent obj3 = new Parent();
        obj3.display();

        Parent obja = new Child();
        obja.display1();  // Output: Parent static display() (method hiding)

        Child objb = new Child();
        objb.display1(); // Output: Child static display()

        Parent objc = new Parent();
        objc.display1();
    }
}

Output: 

Child static display()
Child static display()
Parent static display()
Parent test static display()
Clilc test static display()
Parent test static display()

2. What happens if we change the return type during overriding? // Covariant return types in Java
class Animal {
    public Animal create() {
        return new Animal();
    }

    public void sound() {
        System.out.println("Some animal sound");
    }
}

class Dog extends Animal {
    @Override
    public Dog create() {  // Covariant return type: returning Dog instead of Animal
        return new Dog();
    }

    @Override
    public void sound() {
        System.out.println("Bark");
    }
}

public class CovariantReturnExample {
    public static void main(String[] args) {
        Animal animal = new Dog();  // Polymorphism
        Animal newAnimal = animal.create();  // Returns Dog, but as Animal reference
        newAnimal.sound();  // Output: Bark

        Dog dog = new Dog();
        Dog newDog = dog.create();  // Returns Dog directly without casting
        newDog.sound();  // Output: Bark
    }
}

Explanation: In the Dog class, the sound() method has a covariant return type Dog (which is a subclass of Animal). This is allowed because the return type of the overridden method is either the same or a subclass of the parent’s method return type.

//When a subclass defines a field (data member) with the same name as a field in its superclass, the field in the subclass hides the field in the superclass. This is known as field hiding, not overriding.

class Parent {
    int x = 10; // Parent class field
}

class Child extends Parent {
    int x = 20; // This hides the Parent class field
}

public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        Parent childAsParent = new Child(); // Upcasting

        // Accessing fields
        System.out.println("Parent x: " + parent.x); // Outputs 10
        System.out.println("Child x: " + child.x);   // Outputs 20
        System.out.println("Upcasted Child x: " + childAsParent.x); // Outputs 10
    }
}
// Method hsinding
//The reference type is A, so the show method from class A is called. This demonstrates that static methods are hidden rather than overridden, and the decision about which method to call is made at compile time based on the reference type.
class A {
    static void show() {
        System.out.println("Static show method in A");
    }
}

class B extends A {
    static void show() {
        System.out.println("Static show method in B");
    }
}

public class Test {
    public static void main(String[] args) {
        A a = new B();
        a.show();
    }
}
// Output Static show method in A

//In Java, a class cannot be declared as static at the top level. The concept of static applies to inner classes, but not to outer classes.
class OuterClass {
    private static String staticOuterField = "Static Outer Field";
    private String nonStaticOuterField = "Non-Static Outer Field";
    private String outerField = "Outer Field";

    // Non-static inner class
    class InnerClass {
        void display() {
            // Accessing outer class's field
            System.out.println("Accessing: " + outerField);
        }
    }

    //static inner class
    static class StaticInnerClass {
        void display() {
            // Accessing static member of outer class
            //Only abstract or final keyword is allowed.
            System.out.println("Accessing: " + staticOuterField);
            // Cannot access non-static member of outer class directly
            // System.out.println("Accessing: " + nonStaticOuterField); // This would cause a compilation error
        }
    }
}

public class Test {
    public static void main(String[] args) {
        // when inner class is not static
        //Can access both static and non-static members of the outer class.

        OuterClass outer = new OuterClass(); // Create an instance of OuterClass
        OuterClass.InnerClass inner = outer.new InnerClass(); // Create an instance of InnerClass
        inner.display(); // Output: Accessing: Outer Field

        // when inner class is static
        //Can access only static members of the outer class; cannot directly access non-static members.
        OuterClass.StaticInnerClass staticInner = new OuterClass.StaticInnerClass(); // No need for outer class instance
        staticInner.display(); // Output: Accessing: Static Outer Field
    }
}

// Method-Local Inner Classes and Local Variables:
public class OuterClass {
    void outerMethod() {
        String localVariable = "Hello";

        // Method-local inner class
        class InnerClass {
            void display() {
                // Accessing the local variable
                System.out.println(localVariable);
            }
        }

       // localVariable = "Hi"; // This will give compilation error
        InnerClass inner = new InnerClass();
        inner.display(); // Output will be: Hello (not "Hi")
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.outerMethod();
    }
}
// output : compilation eror at line String localVariable = "Hello";, its should be "final String localVariable = "Hello";"
