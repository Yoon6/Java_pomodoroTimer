import javax.swing.*;
import java.awt.*;

public class windowFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pomodoro");
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setBackground(new Color(0,0,0,122));
        frame.setVisible(true);
    }
}
