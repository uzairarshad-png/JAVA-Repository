/**
 *
 * @author Uzair Arshad
 */
public class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }

    @Override
    public String toString() {
        return "Square\n" +
               "Area=" + getArea() + "\n" +
               "Perimeter=" + getPerimeter();
    }
}