package main.library;

public class point {
    public int x;
    public int y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public point(point pnt) {
        x = pnt.x;
        y = pnt.y;
    }

    @Override
    public String toString() {
        return "point: x("+x+") y("+y+")";
    }
}