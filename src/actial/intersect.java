package actial;

public class intersect {
    public static boolean triangle(point a, point b, point c, point p) {
        double primary = tri_area(a, b, c);
        double a1 = tri_area(a, b, p);
        double a2 = tri_area(a, p, c);
        double a3 = tri_area(p, b, c);

        return (primary == a1 + a2 + a3);
    }

    public static boolean quad(point a, point b, point c, point d, point p) {
        return (triangle(a, b, d, p) || triangle(d, b, c, p));
    }

    // Calculates the area of the triangle
    public static double tri_area(point a, point b, point c) {
        return Math.abs((a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2.0);
    }

    public static class point {
        public int x;
        public int y;

        point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
