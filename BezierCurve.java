import java.awt.*;
import java.util.List;

public class BezierCurve {

    public static Point Point3(float t, List<Point> controlPoints) {
        int N = controlPoints.size() - 1;
        if (N > 16) {
            System.out.println("You have used more than 16 control points. The maximum control points allowed is 16.");
            controlPoints = controlPoints.subList(0, 15);
        }
        if (t <= 0) return controlPoints.get(0);
        if (t >= 1) return controlPoints.get(controlPoints.size() - 1);

        Point p = new Point(0, 0);

        for (int i = 0; i < controlPoints.size(); ++i) {
            float j = Bernstein(N, i, t);
            Point bn = controlPoints.get(i);
            Point bn1 = new Point((int)(bn.x * j), (int)(bn.y * j));
            p = new Point(p.x + bn1.x, p.y + bn1.y);
        }

        return p;
    }

    private static float Bernstein(int n, int i, float t) {
        double t_i = Math.pow(t, i);
        double t_n_minus_i = Math.pow((1 - t), (n - i));

        double basis = Binomial(n, i) * t_i * t_n_minus_i;
        return (float)basis;
    }

    private static float Binomial(int n, int i)
    {
        float ni;
        float a1 = Factorial[n];
        float a2 = Factorial[i];
        float a3 = Factorial[n - i];
        ni = a1 / (a2 * a3);
        return ni;
    }

    private static float[] Factorial = new float[]{
            1.0f,
            1.0f,
            2.0f,
            6.0f,
            24.0f,
            120.0f,
            720.0f,
            5040.0f,
            40320.0f,
            362880.0f,
            3628800.0f,
            39916800.0f,
            479001600.0f,
            6227020800.0f,
            87178291200.0f,
            1307674368000.0f,
            20922789888000.0f,
    };
}
