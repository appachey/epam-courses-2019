package ua.epam.lections.lection2.hometask2;

public class Circle {
    private int x;
    private int y;
    private double radius;

    public Circle() {
        this.x = 0;
        this.y = 0;
        this.radius = 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void moveCircle(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean isPointBelongTo(int x, int y) {

        return distToPoint(x, y) <= Math.pow(this.radius, 2) ? true : false;
    }

    public boolean isCircleBelongTo(Circle circle) {
        if (isPointBelongTo(circle.getX(), circle.getY())) {
            double maxRadius = this.radius - Math.sqrt(distToPoint(circle.getX(), circle.getY()));
            return circle.radius <= maxRadius ? true : false;
        }
        return false;
    }

    private double distToPoint(int x, int y) {
        return (Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }
}
