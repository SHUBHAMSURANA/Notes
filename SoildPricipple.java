1. S - Single Responsibility Principle (SRP)
	A class should have only one reason to change, meaning it should have only one job or responsibility.

Example Before Refactoring

public class UserManager {
    public void createUser(String username, String password) {
        // Validate user data
        // Store user in database
        // Send welcome email
    }
}

Refactored Code (Following SRP):

public class UserManager {
    private UserRepository userRepository;
    private EmailService emailService;
    private UserValidator userValidator;

    public UserManager(UserRepository repo, EmailService emailService, UserValidator validator) {
        this.userRepository = repo;
        this.emailService = emailService;
        this.userValidator = validator;
    }

    public void createUser(String username, String password) {
        userValidator.validate(username, password);
        userRepository.saveUser(username, password);
        emailService.sendWelcomeEmail(username);
    }
}

// Each class now has a single responsibility:
public class UserValidator {
    public void validate(String username, String password) {
        // Validation logic
    }
}

public class EmailService {
    public void sendWelcomeEmail(String username) {
        // Email logic
    }
}

public class UserRepository {
    public void saveUser(String username, String password) {
        // Database logic
    }
}

2. O - Open for extension/Closed for modification Principle (OCP)
=> Use abstraction (interfaces or abstract classes) to allow extension of behavior without modifying existing code.
Implement new features by creating new classes that extend or implement these abstractions rather than modifying existing ones.

//Example Before Refactoring:
public class DiscountService {
    public double applyDiscount(String customerType, double price) {
        if (customerType.equals("regular")) {
            return price * 0.9;
        } else if (customerType.equals("vip")) {
            return price * 0.8;
        }
        return price;
    }
}

//Refactored Code (Following OCP):


public interface Discount {
    double apply(double price);
}

// RegularDiscount.java
public class RegularDiscount implements Discount {
    @Override
    public double apply(double price) {
        return price * 0.9; // 10% discount
    }
}

// VIPDiscount.java
public class VIPDiscount implements Discount {
    @Override
    public double apply(double price) {
        return price * 0.8; // 20% discount
    }
}

// DiscountService.java
public class DiscountService {
    public double applyDiscount(Discount discount, double price) {
        return discount.apply(price);
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();
        
        double originalPrice = 100.0; // Example price

        // Using RegularDiscount
        //5. D - Dependency Inversion Principle (DIP)
        Discount regularDiscount = new RegularDiscount();
        double regularDiscountedPrice = discountService.applyDiscount(regularDiscount, originalPrice);
        System.out.println("Regular Discounted Price: $" + regularDiscountedPrice);

        // Using VIPDiscount
        Discount vipDiscount = new VIPDiscount();
        double vipDiscountedPrice = discountService.applyDiscount(vipDiscount, originalPrice);
        System.out.println("VIP Discounted Price: $" + vipDiscountedPrice);
    }
}

//3. L - Liskov Substitution Principle (LSP)
//If class B is subtype of class A, then we should be able to replace object of A with B without breaking the behavior of the program.
//The Liskov Substitution Principle (LSP) is about substitutability at the reference level, not the object level.

class A {
    public void performAction() {
        System.out.println("Performing action in class A");
    }
}

class B extends A {
    @Override
    public void performAction() {
        System.out.println("Performing action in class B");
    }
}

public class Main {
    public static void main(String[] args) {
        A objA = new A();
        objA.performAction(); // Output: Performing action in class A

        B objB = new B();
        objB.performAction(); // Output: Performing action in class B

        A objAB = new B(); // Substituting object of B for A
        objAB.performAction(); // Output: Performing action in class B
    }
}

//In the last part of the code, we demonstrate the Liskov Substitution Principle by creating an object objAB of type A but assigning it an instance of class B.
// According to the LSP, we should be able to substitute objects of class B for objects of class A without breaking the behavior of the program.


4. I - Interface Segregation Principle (ISP)
Break down large interfaces into smaller, beause larger interface may have methods it does not use by sub class.
Ensure that classes implement only the interfaces that they need.

Example Before Refactoring:

public interface Worker {
    void work();
    void eat();
}

public class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("Robot working...");
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robot doesn't eat");
    }
}

By splitting the interface into Workable and Eatable, the RobotWorker class is no longer forced to implement the eat method.

public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public class HumanWorker implements Workable, Eatable {
    public void work() {
        System.out.println("Human working...");
    }

    public void eat() {
        System.out.println("Human eating...");
    }
}

public class RobotWorker implements Workable {
    public void work() {
        System.out.println("Robot working...");
    }
}

5. D - Dependency Inversion Principle (DIP)
Class should depend on interface rather than concrete classes.

class MacBook {
    WiredKeyBorad keyboard;
    WiredMouse mouse;

    MacBook() {
        // HERE Macbook depend on WiredKeyBorad AND WiredMouse are concreet class.
        keyboard = new WiredKeyBorad();
        mouse = new WiredMouse();
    }
}


To solve it 

can you please provide fill code for below snipt

class MacBook {
    // both are interfce
    keyboard keyboard;
    Mouse mouse;

    // let make it generic such that it can hable any concrete class combination
    MacBook(keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard; // we can pass bluetoothKeyboard object or WiredKeyBorad object.
        this.mouse = mouse;
    }    
}    

************full coder***********
// Interface for Keyboard
interface Keyboard {
    void type();
}

// Concrete class for Bluetooth Keyboard
class BluetoothKeyboard implements Keyboard {
    @Override
    public void type() {
        System.out.println("Typing using Bluetooth Keyboard.");
    }
}

// Concrete class for Wired Keyboard
class WiredKeyboard implements Keyboard {
    @Override
    public void type() {
        System.out.println("Typing using Wired Keyboard.");
    }
}

// Interface for Mouse
interface Mouse {
    void click();
}

// Concrete class for Optical Mouse
class OpticalMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Clicking with Optical Mouse.");
    }
}

// Concrete class for Wireless Mouse
class WirelessMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Clicking with Wireless Mouse.");
    }
}

// MacBook class
class MacBook {
    private Keyboard keyboard;
    private Mouse mouse;

    // Constructor to accept any Keyboard and Mouse combination
    MacBook(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public void use() {
        keyboard.type();
        mouse.click();
    }
}

// Main class to demonstrate functionality
public class Main {
    public static void main(String[] args) {
        Keyboard bluetoothKeyboard = new BluetoothKeyboard();
        Mouse opticalMouse = new OpticalMouse();

        MacBook macBook1 = new MacBook(bluetoothKeyboard, opticalMouse);
        macBook1.use();

        Keyboard wiredKeyboard = new WiredKeyboard();
        Mouse wirelessMouse = new WirelessMouse();

        MacBook macBook2 = new MacBook(wiredKeyboard, wirelessMouse);
        macBook2.use();
    }
}

Domain-Driven Design (DDD) for organizing your domain model and ensuring that the core business logic is well-structured and focused on the domain.
Aspect-Oriented Programming (AOP) for handling cross-cutting concerns, like logging and transactions, in a clean and maintainable way, allowing you to focus on the domain logic.





