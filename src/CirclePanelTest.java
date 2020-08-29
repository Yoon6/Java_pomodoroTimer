import javax.swing.*;
import java.awt.*;

public class CirclePanelTest {

    public CirclePanelTest() {
        initComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CirclePanelTest();
            }
        });
    }

    private void initComponents() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0, 0));
        final DrawingClass dc = new DrawingClass();
        JPanel testPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                dc.draw(g2d, getWidth(), getHeight());

            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 300);
            }
        };

        testPanel.setBackground(new Color(0,0,0,0));
        frame.add(testPanel);

        frame.pack();
        frame.setVisible(true);
    }
}

class DrawingClass {

    public void draw(Graphics2D g2d, int w, int h) {
        g2d.setColor(Color.BLACK);
        g2d.fillOval(5, 5, w / 2, h / 2);
    }
}