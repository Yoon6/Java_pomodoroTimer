import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// mainFrame과 settingFrame의 공통부분
public class defaultFrame extends JFrame {
    int posX=0, posY=0;

    defaultFrame(String name){
        super(name);

        ImageIcon img = new ImageIcon("res/App_Icon.png");

        setIconImage(img.getImage());

        setUndecorated(true);
        setAlwaysOnTop(true);

        this.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                posX=e.getX();
                posY=e.getY();
            }
        });

        this.addMouseMotionListener(new MouseAdapter()
        {
            public void mouseDragged(MouseEvent evt)
            {
                //sets frame position when mouse dragged
                setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);

            }
        });
    }
}
