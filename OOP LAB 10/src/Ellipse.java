/**
 *
 * @author Uzair Arshad
 */
public class Ellipse extends Shape implements Eccentric {

    protected double a; // semi-major axis
    protected double b; // semi-minor axis

    public Ellipse(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getArea() {
        return Math.PI * a * b;
    }

    @Override
    public double getPerimeter() {
        // Ramanujan approximation
        return Math.PI * (3 * (a + b) - Math.sqrt((3 * a + b) * (a + 3 * b)));
    }

    @Override
    public double getEccentricity() {
        double major = Math.max(a, b);
        double minor = Math.min(a, b);
        return Math.sqrt(1 - (minor * minor) / (major * major));
    }

    @Override
    public String toString() {
        return "Ellipse\n" +
               "Area=" + getArea() + "\n" +
               "Perimeter=" + getPerimeter() + "\n" +
               "Eccentricity=" + getEccentricity();
    }
}