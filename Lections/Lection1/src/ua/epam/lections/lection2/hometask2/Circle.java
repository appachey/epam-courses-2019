package ua.epam.lections.lection2.hometask2;

public class Circle {
    private int x;
    private int y;
    private int radius;

    public Circle(){
        this.x = 0;
        this.y = 0;
        this.radius = 1;
    }

    public Circle (int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void moveCircle (int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public boolean isPointBelongTo (int x, int y){
        int radius2 = (int) (Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
        return radius2 <= (int) Math.pow(this.radius, 2) ? true : false;
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
