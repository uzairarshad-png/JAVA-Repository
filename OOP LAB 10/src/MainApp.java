/**
 *
 * @author Uzair Arshad
 */
public class MainApp {

    public static void main(String[] args) {

        // 3 Students
        Student s1 = new Student("Ali", "Khan", 20, 'A');
        Student s2 = new Student("Sara", "Ahmed", 19, 'B');
        Student s3 = new Student("Usman", "Raza", 21, 'C');

        // 3 Staff
        Staff st1 = new Staff("John", "Doe", 35, 50000);
        Staff st2 = new Staff("Anna", "Smith", 40, 60000);
        Staff st3 = new Staff("David", "Lee", 45, 70000);

        // Print Students
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println();

        // Print Staff
        System.out.println(st1);
        System.out.println("Annual Payment: " + st1.getPayment());

        System.out.println(st2);
        System.out.println("Annual Payment: " + st2.getPayment());

        System.out.println(st3);
        System.out.println("Annual Payment: " + st3.getPayment());
    }
}

interface Payable {
    double getPayment();
}

abstract class Person {

    protected String firstName;
    protected String lastName;
    protected int age;

    public Person(String fn, String ln, int age) {
        this.firstName = fn;
        this.lastName = ln;
        this.age = age;
    }

    public abstract String toString();
}

class Student extends Person {

    private char grade;

    public Student(String fn, String ln, int age, char grade) {
        super(fn, ln, age);
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student: " + firstName + ", " + lastName + ", " + age + ", " + grade;
    }
}

class Staff extends Person implements Payable {

    private double salary; // monthly salary

    public Staff(String fn, String ln, int age, double salary) {
        super(fn, ln, age);
        this.salary = salary;
    }

    @Override
    public double getPayment() {
        return salary * 12; // annual salary
    }

    @Override
    public String toString() {
        return "Staff: " + firstName + ", " + lastName + ", " + age + ", " + salary;
    }
}