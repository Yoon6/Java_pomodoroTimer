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


    // girdbaglayout 설정
    public void make(JComponent c, int x, int y, int w, int h) {

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;

        grid.setConstraints(c, gbc);
        // GridBagLayout의 GridBagConstraints의 set하는 방법
    }

    public restFrame(){

        ImageIcon img = new ImageIcon("res/App_Icon.png");

        setIconImage(img.getImage());

        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈

        setLayout(grid);

        gbc.fill=GridBagConstraints.CENTER;
        gbc.weightx=1.0;
        gbc.weighty=1.0;

        setSize(screenSize);
        setLocation(0,0);

        setUndecorated(true);
        setAlwaysOnTop(true);

        setBackground(new Color(0,0,0,150));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // 레이블 설정
        time = new JLabel(" ");
        time.setFont(new Font("Arial",Font.PLAIN,20)); //폰트
        time.setForeground(Color.WHITE); // 글자 컬러
        time.setBackground(new Color(0,0,0,0)); // 배경 컬러(rgb,alpha(투명도))

        explanation1 = new JLabel("Protect your eyes!");
        explanation1.setFont(new Font("Arial",Font.PLAIN,20));
        explanation1.setForeground(Color.WHITE);
        explanation1.setBackground(new Color(0,0,0,0));

        explanation2 = new JLabel("Close with ESC");
        explanation2.setFont(new Font("Arial",Font.PLAIN,20));
        explanation2.setForeground(Color.WHITE);
        explanation2.setBackground(new Color(0,0,0,0));

        addKeyListener(new KeyListener() { // 키보드 리스너
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==27){ // esc키 눌렀을 때.
                    dispose();
                    mainFrame.break_time_out=true;
                }
            }
        });
        JLabel dumb1 = new JLabel();
        JLabel dumb2 = new JLabel();

        make(dumb1,0,0,1,1);
        make(explanation1,1,0,1,1);
        make(time,2,0,1,1);
        make(explanation2,3,0,1,1);
        make(dumb2,4,0,1,1);


        add(time);
        add(explanation1);
        add(explanation2);
        add(dumb1);
        add(dumb2);
        setVisible(true);
        toFront();
        requestFocus();
    }
}
