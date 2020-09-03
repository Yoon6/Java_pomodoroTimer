import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainFrame extends JFrame implements ActionListener{

    int posX=0, posY=0;
    int setTime = 20;

    static boolean settingIsOpened=false;

    // Button
    JButton btn_close;
    JButton btn_minimize;
    JButton btn_setting;
    JButton btn_Play;

    // Image
    ImageIcon img_setting = new ImageIcon("res/baseline_settings_white_18dp.png");
    ImageIcon img_minimize = new ImageIcon("res/outline_minimize_white_18dp.png");
    ImageIcon img_close = new ImageIcon("res/outline_clear_white_18dp.png");
    ImageIcon img_play = new ImageIcon("res/baseline_play_arrow_white_48dp.png");
    Image tmp_set = img_setting.getImage().getScaledInstance(18,18,Image.SCALE_SMOOTH);
    Image tmp_minimize = img_minimize.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
    Image tmp_close = img_close.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
    Image tmp_play = img_play.getImage().getScaledInstance(48,48,Image.SCALE_SMOOTH);

    // Panel
    Container contentPanel = this.getContentPane();
    JPanel panel = new JPanel();

    FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);

    public CustomPanel circleBar_panel = new CustomPanel();

    public mainFrame(String title){

        super(title);

        // JFrame
        setSize(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0,0,0,1));
        setAlwaysOnTop(true);

        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙


        // JButton
        btn_setting = new JButton(new ImageIcon(tmp_set));
        btn_minimize = new JButton(new ImageIcon(tmp_minimize));
        btn_close = new JButton(new ImageIcon(tmp_close));
        btn_Play = new JButton(new ImageIcon(tmp_play));

        createButton(btn_setting);
        createButton(btn_minimize);
        createButton(btn_close);
        createButton(btn_Play);


        // listener
        btn_setting.addActionListener(this);
        btn_minimize.addActionListener(this);
        btn_close.addActionListener(this);
        btn_Play.addActionListener(this);
        // set location and size
        btn_setting.setBounds(90,133,20,20);
        btn_minimize.setBounds(180,5,20,20);
        btn_close.setBounds(90,50,20,20);
        btn_Play.setBounds(76,77,48,48);


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



        // JPanel
        contentPanel.add(btn_setting);
        //contentPanel.add(btn_minimize);
        contentPanel.add(btn_close);
        contentPanel.add(btn_Play);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(flowLayout);

        circleBar_panel.setBackground(new Color(0,128,128,0));

        contentPanel.add(panel);
        contentPanel.add(circleBar_panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btn_setting.equals(e.getSource())) {
            System.out.println("setting");
            if(!settingIsOpened){
                new settingFrame();
                settingIsOpened=true;
            }
        }else if(btn_minimize.equals(e.getSource())){
            System.out.println("minimize");
            // minimize
            setState(Frame.ICONIFIED);
        }else if(btn_close.equals(e.getSource())){
            System.out.println("close");
            // close
            System.exit(0);
        }else if(btn_Play.equals(e.getSource())){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(long num = setTime*60*1000; num>=0; num-=1000){
                        circleBar_panel.UpdateProgress(num,setTime);
                        circleBar_panel.repaint();
                        try {
                            Thread.sleep(1000);
                            if(num==0){
                                repaint();
                                btn_Play.setVisible(true);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            btn_Play.setVisible(false);
        }
    }

    public void createButton(JButton btn){
        //btn.setPreferredSize(new Dimension(20,20));
        btn.setBackground(Color.red);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
    }

    }
