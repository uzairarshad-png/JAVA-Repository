/**
 *
 * @author Uzair Arshad
 */
import java.util.Scanner;
class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    // Returns the straight-line distance between two Point objects
    public double distanceTo(Point p) {
        int dx = this.x - p.getX();
        int dy = this.y - p.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

abstract class Shape {

    private Point center;

    public Shape(Point center) {
        this.center = center;
    }

    public Point getCenter() { return center; }

    // Each subclass decides what "inside the shape" means
    public abstract boolean contains(Point p);

    // Each subclass provides its own area formula
    public abstract double area();
}

class Circle extends Shape {

    private int radius;

    public Circle(Point center, int radius) {
        super(center);
        this.radius = radius;
    }

    public int getRadius() { return radius; }

    @Override
    public boolean contains(Point p) {
        return getCenter().distanceTo(p) <= radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle    [center=" + getCenter()
                + ", radius=" + radius
                + ", area="   + String.format("%.2f", area()) + "]";
    }
}

class Rectangle extends Shape {

    private int length;
    private int width;

    public Rectangle(Point center, int length, int width) {
        super(center);
        this.length = length;
        this.width  = width;
    }

    public int getLength() { return length; }
    public int getWidth()  { return width;  }

    @Override
    public boolean contains(Point p) {
        int cx = getCenter().getX();
        int cy = getCenter().getY();
        boolean withinX = p.getX() >= (cx - length / 2) && p.getX() <= (cx + length / 2);
        boolean withinY = p.getY() >= (cy - width  / 2) && p.getY() <= (cy + width  / 2);
        return withinX && withinY;
    }

    @Override
    public double area() {
        return (double) length * width;
    }

    @Override
    public String toString() {
        return "Rectangle [center=" + getCenter()
                + ", length=" + length
                + ", width="  + width
                + ", area="   + String.format("%.2f", area()) + "]";
    }
}

class ShapesArray {

    private Shape[] shapes;
    private int     count;

    public ShapesArray(int size) {
        shapes = new Shape[size];
        count  = 0;
    }

    // Adds a shape — returns false if the array is already full
    public boolean addShape(Shape s) {
        if (count < shapes.length) {
            shapes[count] = s;
            count++;
            return true;
        }
        System.out.println("Array is full! Cannot add any more shapes.");
        return false;
    }

    // Prints details of every Rectangle stored in the array
    public void displayrectsinfo() {
        System.out.println("\n--- All Rectangles ---");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (shapes[i] instanceof Rectangle) {
                System.out.println("  " + shapes[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("  No rectangles currently in the array.");
        }
    }

    // Returns the total number of Circle objects in the array
    public int getCirclecounter() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (shapes[i] instanceof Circle) {
                total++;
            }
        }
        return total;
    }

    // Computes and returns the average area across all stored shapes
    public double getAvgAreas() {
        if (count == 0) return 0.0;
        double sum = 0.0;
        for (int i = 0; i < count; i++) {
            sum += shapes[i].area();
        }
        return sum / count;
    }

    // Removes every Rectangle from the array, keeping only non-rectangles
    public void removeallrect() {
        Shape[] updated  = new Shape[shapes.length];
        int     newCount = 0;
        for (int i = 0; i < count; i++) {
            if (!(shapes[i] instanceof Rectangle)) {
                updated[newCount] = shapes[i];
                newCount++;
            }
        }
        shapes = updated;
        count  = newCount;
        System.out.println("All rectangles have been removed.");
    }

    public int getCount() { return count; }
}

public class Exercise2 {

    static void runPart_a() {
        System.out.println("        PART (a)  -  Shape Class Definitions         ");
        
        // Test Point and distanceTo()
        Point pt1 = new Point(0, 0);
        Point pt2 = new Point(6, 8);
        System.out.println("Point 1 : " + pt1);
        System.out.println("Point 2 : " + pt2);
        System.out.printf("Distance between them : %.2f%n", pt1.distanceTo(pt2)); // expected 10.00

        // Test Circle contains()
        Circle c = new Circle(new Point(0, 0), 7);
        System.out.println("\nCreated : " + c);
        System.out.println("Contains (3, 4)  [dist=5.00 , expect true ] : " + c.contains(new Point(3, 4)));
        System.out.println("Contains (6, 6)  [dist=8.49 , expect false] : " + c.contains(new Point(6, 6)));

        // Test Rectangle contains()
        Rectangle r = new Rectangle(new Point(0, 0), 12, 8);
        System.out.println("\nCreated : " + r);
        System.out.println("Contains (5, 3)  [expect true ] : " + r.contains(new Point(5, 3)));
        System.out.println("Contains (7, 5)  [expect false] : " + r.contains(new Point(7, 5)));

        // Test ShapesArray methods
        ShapesArray arr = new ShapesArray(10);
        arr.addShape(new Circle(new Point(1, 2), 4));
        arr.addShape(new Circle(new Point(3, 5), 6));
        arr.addShape(new Rectangle(new Point(0, 0), 10, 5));
        arr.addShape(new Rectangle(new Point(4, 4), 8, 3));

        System.out.println("\n--- ShapesArray Tests ---");
        System.out.println("Total shapes stored  : " + arr.getCount());
        System.out.println("Number of circles    : " + arr.getCirclecounter());
        System.out.printf("Average area         : %.2f%n", arr.getAvgAreas());
        arr.displayrectsinfo();
        arr.removeallrect();
        System.out.println("Shapes remaining after rectangle removal : " + arr.getCount());

        System.out.println("\n===== Part (a) Complete =====\n");
    }

    static void runPart_b() {
        System.out.println("       PART (b)  -  Interactive Shape Manager         ");

        Scanner    scanner    = new Scanner(System.in);
        ShapesArray shapesArray = new ShapesArray(20);
        int        choice     = 0;

        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Each case is wrapped in { } to allow local variable declarations
            switch (choice) {

                case 1: {
                    // ── Add a new shape ─────────────────────────
                    System.out.println("  a. Rectangle");
                    System.out.println("  b. Circle");
                    System.out.print("Select shape type (a/b): ");
                    char type = scanner.next().toLowerCase().charAt(0);

                    if (type == 'a') {
                        System.out.print("Enter center x : ");
                        int cx = scanner.nextInt();
                        System.out.print("Enter center y : ");
                        int cy = scanner.nextInt();
                        System.out.print("Enter length   : ");
                        int len = scanner.nextInt();
                        System.out.print("Enter width    : ");
                        int wid = scanner.nextInt();
                        Rectangle rect = new Rectangle(new Point(cx, cy), len, wid);
                        if (shapesArray.addShape(rect)) {
                            System.out.println("Added: " + rect);
                        }
                    } else if (type == 'b') {
                        System.out.print("Enter center x : ");
                        int cx = scanner.nextInt();
                        System.out.print("Enter center y : ");
                        int cy = scanner.nextInt();
                        System.out.print("Enter radius   : ");
                        int rad = scanner.nextInt();
                        Circle circle = new Circle(new Point(cx, cy), rad);
                        if (shapesArray.addShape(circle)) {
                            System.out.println("Added: " + circle);
                        }
                    } else {
                        System.out.println("Invalid input. Enter 'a' for Rectangle or 'b' for Circle.");
                    }
                    break;
                }

                case 2: {
                    // ── Display all rectangles ──────────────────
                    shapesArray.displayrectsinfo();
                    break;
                }

                case 3: {
                    // ── Display average area ────────────────────
                    System.out.printf("Average area of all shapes: %.2f%n",
                            shapesArray.getAvgAreas());
                    break;
                }

                case 4: {
                    // ── Display circle count ────────────────────
                    System.out.println("Total circles in array: " + shapesArray.getCirclecounter());
                    break;
                }

                case 5: {
                    // ── Remove all rectangles ───────────────────
                    shapesArray.removeallrect();
                    break;
                }

                case 6: {
                    // ── Exit ────────────────────────────────────
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                }

                default: {
                    System.out.println("Invalid option. Please select a number between 1 and 6.");
                }
            }

        } while (choice != 6);

        scanner.close();
        System.out.println("\n===== Part (b) Complete =====\n");
    }

    // Helper method — prints the menu options to the console
    private static void printMenu() {
        System.out.println("\n+--------------------------------+");
        System.out.println("|      Shape Manager Menu        |");
        System.out.println("+--------------------------------+");
        System.out.println("| 1. Add a new shape             |");
        System.out.println("| 2. Display all rectangles      |");
        System.out.println("| 3. Show average area           |");
        System.out.println("| 4. Count circles               |");
        System.out.println("| 5. Remove all rectangles       |");
        System.out.println("| 6. Exit                        |");
        System.out.println("+--------------------------------+");
    }

    // ── main : runs Part (a) then launches Part (b) menu ───────
    public static void main(String[] args) {
        runPart_a();
        runPart_b();
    }
}