import javax.swing.*;
import java.awt.*;

public class windowFrame extends JFrame{
    public windowFrame(String title){
        super(title);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0,0,0,122));
        setVisible(true);
    }
}
