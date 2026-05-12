import java.util.*;
import java.io.*;

public class Main {

    static class Point implements Comparable<Point> {

        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point other) {

            if (Double.compare(this.x, other.x) == 0) {
                return Double.compare(this.y, other.y);
            }

            return Double.compare(this.x, other.x);
        }
    }

    static double cross(Point O, Point A, Point B) {

        return (A.x - O.x) * (B.y - O.y)
                - (A.y - O.y) * (B.x - O.x);
    }

    static double distance(Point A, Point B) {

        double dx = A.x - B.x;
        double dy = A.y - B.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    static List<Point> convexHull(List<Point> points) {

        Collections.sort(points);

        int n = points.size();

        if (n <= 1) {
            return points;
        }

        List<Point> lower = new ArrayList<>();

        for (Point p : points) {

            while (lower.size() >= 2 &&
                    cross(
                            lower.get(lower.size() - 2),
                            lower.get(lower.size() - 1),
                            p
                    ) <= 0) {

                lower.remove(lower.size() - 1);
            }

            lower.add(p);
        }

        List<Point> upper = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {

            Point p = points.get(i);

            while (upper.size() >= 2 &&
                    cross(
                            upper.get(upper.size() - 2),
                            upper.get(upper.size() - 1),
                            p
                    ) <= 0) {

                upper.remove(upper.size() - 1);
            }

            upper.add(p);
        }

        if (lower.size() > 0) lower.remove(lower.size() - 1);
        if (upper.size() > 0) upper.remove(upper.size() - 1);

        lower.addAll(upper);

        return lower;
    }

    public static void main(String[] args) throws Exception {

//        BufferedReader br =
//                new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("Enter number of rectangles:");
//
//        int N = Integer.parseInt(br.readLine().trim());
//
//        List<Point> points = new ArrayList<>();
//
//        System.out.println("Enter rectangle coordinates:");
//        System.out.println("Format: x1 y1 x2 y2 (Consider Space as well - use enter after 1 rectangle coordinates)");
//
//        for (int i = 0; i < N; i++) {
//
//            StringTokenizer st =
//                    new StringTokenizer(br.readLine());
//
//            double x1 = Double.parseDouble(st.nextToken());
//            double y1 = Double.parseDouble(st.nextToken());
//            double x2 = Double.parseDouble(st.nextToken());
//            double y2 = Double.parseDouble(st.nextToken());
//
//            double left = Math.min(x1, x2);
//            double right = Math.max(x1, x2);
//
//            double bottom = Math.min(y1, y2);
//            double top = Math.max(y1, y2);
//
//            points.add(new Point(left, bottom));
//            points.add(new Point(left, top));
//            points.add(new Point(right, bottom));
//            points.add(new Point(right, top));
//        }
//
//        List<Point> hull = convexHull(points);
//
//        double perimeter = 0.0;
//
//        int m = hull.size();
//
//        for (int i = 0; i < m; i++) {
//
//            Point a = hull.get(i);
//            Point b = hull.get((i + 1) % m);
//
//            perimeter += distance(a, b);
//        }
//
//        System.out.printf("Minimum Patrol Path Length: %.6f\n", perimeter);
//    }


        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        List<Point> points = new ArrayList<>();

        for (int i = 0; i < N; i++) {

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            double x1 = Double.parseDouble(st.nextToken());
            double y1 = Double.parseDouble(st.nextToken());
            double x2 = Double.parseDouble(st.nextToken());
            double y2 = Double.parseDouble(st.nextToken());

            double left = Math.min(x1, x2);
            double right = Math.max(x1, x2);
            double bottom = Math.min(y1, y2);
            double top = Math.max(y1, y2);

            points.add(new Point(left, bottom));
            points.add(new Point(left, top));
            points.add(new Point(right, bottom));
            points.add(new Point(right, top));
        }

        List<Point> hull = convexHull(points);

        double perimeter = 0.0;

        int m = hull.size();

        for (int i = 0; i < m; i++) {

            Point a = hull.get(i);
            Point b = hull.get((i + 1) % m);

            perimeter += distance(a, b);
        }

        System.out.printf("%.6f\n", perimeter);

        ///Note that commented lines are for the explained system print lines and the other is for getting only the number
    }
}