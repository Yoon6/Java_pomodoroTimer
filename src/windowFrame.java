import ProgressBar.CustomPanel;
import ProgressBar.ProgressBarDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class windowFrame extends JFrame implements ActionListener{

    int posX=0, posY=0;

    // Button
    JButton btn_close;
    JButton btn_dragbar;
    JButton btn_minimize;
    JButton btn_setting;

    // Image
    ImageIcon img_setting = new ImageIcon("res/baseline_settings_white_18dp.png");
    ImageIcon img_dragbar = new ImageIcon("res/outline_menu_white_18dp.png");
    ImageIcon img_minimize = new ImageIcon("res/outline_minimize_white_18dp.png");
    ImageIcon img_close = new ImageIcon("res/outline_clear_white_18dp.png");
    Image tmp_set = img_setting.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
    Image tmp_dragbar = img_dragbar.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
    Image tmp_minimize = img_minimize.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
    Image tmp_close = img_close.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);

    // Panel
    Container contentPanel = this.getContentPane();
    JPanel panel = new JPanel();

    FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);

    public CustomPanel timer_panel = new CustomPanel();

    public windowFrame(String title){

        super(title);

        // JFrame
        setSize(230,230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0,0,0,60));
        setAlwaysOnTop(true);

        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙


        // JButton
        btn_setting = new JButton(new ImageIcon(tmp_set));
        btn_dragbar = new JButton(new ImageIcon(tmp_dragbar));
        btn_minimize = new JButton(new ImageIcon(tmp_minimize));
        btn_close = new JButton(new ImageIcon(tmp_close));
        createButton(btn_setting);
        createButton(btn_dragbar);
        createButton(btn_minimize);
        createButton(btn_close);
        // listener
        btn_setting.addActionListener(this);
        btn_dragbar.addActionListener(this);
        btn_minimize.addActionListener(this);
        btn_close.addActionListener(this);
        // set location and size
        btn_setting.setBounds(5,5,20,20);
        //btn_dragbar.setBounds(140,5,20,20);
        btn_minimize.setBounds(180,5,20,20);
        btn_close.setBounds(205,5,20,20);


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
        contentPanel.add(btn_dragbar);
        contentPanel.add(btn_minimize);
        contentPanel.add(btn_close);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(flowLayout);
        contentPanel.add(panel);

        timer_panel.setBackground(new Color(0,128,128,0));
        contentPanel.add(timer_panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btn_setting.equals(e.getSource())) {
            System.out.println("setting");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int num=100; num>=0; num--){
                        timer_panel.UpdateProgress(num);
                        timer_panel.repaint();
                        try {
                            Thread.sleep(50);
                            if(num==0){
                                timer_panel.UpdateProgress(100);
                                timer_panel.repaint();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }else if(btn_dragbar.equals(e.getSource())){
            System.out.println("dragbar");
        }else if(btn_minimize.equals(e.getSource())){
            System.out.println("minimize");
            // minimize
            setState(Frame.ICONIFIED);
        }else if(btn_close.equals(e.getSource())){
            System.out.println("close");
            // close
            System.exit(0);
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
