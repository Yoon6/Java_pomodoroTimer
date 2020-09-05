import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class settingFrame extends defaultFrame implements ActionListener{

    JButton btn_set_close;
    ImageIcon img_close = new ImageIcon("res/baseline_clear_white_36dp.png");
    Image tmp_close = img_close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);


    settingFrame(){
        super("Settings");
        setSize(300,200);


        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setBackground(new Color(0,0,0,122));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btn_set_close = new JButton(new ImageIcon(tmp_close));
        //createButton(btn_close);
        btn_set_close.addActionListener(this);

        add(btn_set_close);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn_set_close){
            mainFrame.settingIsOpened=false;
            dispose();
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
