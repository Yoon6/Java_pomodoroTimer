import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class settingFrame extends defaultFrame implements ActionListener{

    JButton btn_close;
    ImageIcon img_close = new ImageIcon("res/baseline_clear_white_36dp.png");
    Image tmp_close = img_close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);

    settingPanel sp = new settingPanel();
    JPanel panel = new JPanel();

    settingFrame(){
        super("Settings");
        setSize(300,200);
        setBackground(new Color(0,0,0,122));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        btn_close = new JButton(new ImageIcon(tmp_close));
        createButton(btn_close);
        btn_close.addActionListener(this);


        sp.setBackground(new Color(0,0,0,0));
        panel.setBackground(new Color(0,0,0,0));



        panel.add(btn_close);
        add(panel);
        add(sp);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btn_close){
            mainFrame.settingIsOpened=false;
            dispose();
        }
    }

    public void createButton(JButton btn){
        btn.setPreferredSize(new Dimension(24,24));
        btn.setBackground(Color.red);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
    }


}
