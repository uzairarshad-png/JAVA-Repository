/**
 *
 * @author DANISH LAPTOP
 */
public class Rectangle extends Shape {

    protected double length;
    protected double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "Rectangle\n" +
               "Area=" + getArea() + "\n" +
               "Perimeter=" + getPerimeter();
    }
}