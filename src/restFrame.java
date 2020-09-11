import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class restFrame extends JFrame{
    JLabel time;
    JLabel explanation1;
    JLabel explanation2;
    GridBagLayout grid = new GridBagLayout();

    GridBagConstraints gbc = new GridBagConstraints();



    public void make(JComponent c, int x, int y, int w, int h) {

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;

        grid.setConstraints(c, gbc);
        // GridBagLayout의 GridBagConstraints의 set하는 방법
    }

    public restFrame(){Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙

        setLayout(grid);

        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;

        setSize(screenSize);
        setLocation(0,0);

        setUndecorated(true);
        setAlwaysOnTop(true);

        setBackground(new Color(0,0,0,122));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        time = new JLabel(" ");
        time.setFont(new Font("Arial",Font.PLAIN,20));
        time.setForeground(Color.WHITE);
        time.setBackground(new Color(0,0,0,0));

        explanation1 = new JLabel("Protect your eyes!");
        explanation1.setFont(new Font("Arial",Font.PLAIN,20));
        explanation1.setForeground(Color.WHITE);
        explanation1.setBackground(new Color(0,0,0,0));

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
                    mainFrame.break_time_out=true;
                }
            }
        });

        make(explanation1,0,0,1,1);
        make(time,0,1,1,1);

        add(time);
        setVisible(true);
        toFront();
        requestFocus();
    }
}
