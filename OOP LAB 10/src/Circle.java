/**
 *
 * @author Uzair Arshad
 */
public class Circle extends Ellipse {

    public Circle(double radius) {
        super(radius, radius);
    }

    @Override
    public String toString() {
        return "Circle\n" +
               "Area=" + getArea() + "\n" +
               "Perimeter=" + getPerimeter() + "\n" +
               "Eccentricity=" + getEccentricity();
    }
}