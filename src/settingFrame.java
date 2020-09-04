import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class settingFrame extends JFrame implements ActionListener{

    int posX=0, posY=0;

    JComboBox<Integer> comboBox;

    static int ttime = 19;
    JButton btn_minimize;
    JButton btn_close;
    ImageIcon img_minimize = new ImageIcon("res/outline_minimize_white_18dp.png");
    ImageIcon img_close = new ImageIcon("res/baseline_clear_white_36dp.png");
    Image tmp_minimize = img_minimize.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
    Image tmp_close = img_close.getImage().getScaledInstance(36,36,Image.SCALE_SMOOTH);


    settingFrame(){
        super("Settings");
        setSize(500,300);

        setLayout(new FlowLayout());
        setUndecorated(true);
        setBackground(new Color(0,0,0,122));
        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙

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

        comboBox = new JComboBox<Integer>();
        for(int i=1; i<=60; i++){
            comboBox.addItem(Integer.valueOf(i));
        }
        comboBox.addItemListener(this::itemStateChanged);
        comboBox.setSelectedIndex(ttime);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        btn_minimize = new JButton(new ImageIcon(tmp_minimize));
        btn_close = new JButton(new ImageIcon(tmp_close));

        createButton(btn_minimize);
        createButton(btn_close);
        btn_minimize.addActionListener(this);
        btn_close.addActionListener(this);

        //add(btn_minimize);
        add(btn_close);
        add(comboBox);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_close){
            mainFrame.settingIsOpened=false;
            dispose();
        }else if(e.getSource()==btn_minimize){
            setState(Frame.ICONIFIED);
        }
    }

    public void createButton(JButton btn){
        //btn.setPreferredSize(new Dimension(20,20));
        btn.setBackground(Color.red);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
    }

    public void itemStateChanged(ItemEvent ie) {
        if(!mainFrame.isPlay) {
            ttime = (int) comboBox.getSelectedItem() - 1;
            mainFrame.setSetTime(ttime + 1);
        }
    }
}
