import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class restFrame extends JFrame implements Runnable{
    public restFrame(){}

    @Override
    public void run() {
        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙

        setSize(screenSize);
        setLocation(0,0);

        setUndecorated(true);
        setAlwaysOnTop(true);

        setBackground(new Color(0,0,0,122));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel time = new JLabel("Hello");
        time.setFont(new Font("Arial",Font.PLAIN,30));
        time.setForeground(Color.WHITE);
        time.setBackground(new Color(0,0,0,0));



        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==27){
                    dispose();

                }
            }
        });

        add(time);
        setVisible(true);
    }

    public void pause(){
        Timer t1 = new Timer();
    }
}
