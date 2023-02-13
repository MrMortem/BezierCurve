import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class main extends JPanel {
    private List<Point> points = new ArrayList<>();

    public main() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    points.add(e.getPoint());
                    repaint();
                } else {
                    points.clear();
                    repaint();
                }
            }
        });
    }

    final int pNum = 16;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.blue);

        if(!points.isEmpty()) {
            Point last = points.get(0);
            for (float i = 0f; i < 1; i += 1f / pNum) {
                Point p = BezierCurve.Point3(i, points);
                g2d.drawLine(p.x, p.y, last.x, last.y);
                last = p;
            }
        }

        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(5));

        for(Point p : points) {
            g2d.drawOval(p.x, p.y, 2, 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bézier Curve");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new main());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
