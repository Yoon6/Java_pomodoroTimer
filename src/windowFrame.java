import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class windowFrame extends JFrame implements ActionListener{
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
    public windowFrame(String title){

        super(title);

        // JFrame
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0,0,0,122));
        setAlwaysOnTop(true);

        // JButton
        btn_setting = new JButton(new ImageIcon(tmp_set));
        btn_dragbar = new JButton(new ImageIcon(tmp_dragbar));
        btn_minimize = new JButton(new ImageIcon(tmp_minimize));
        btn_close = new JButton(new ImageIcon(tmp_close));
        createButton(btn_setting);
        createButton(btn_dragbar);
        createButton(btn_minimize);
        createButton(btn_close);
        btn_setting.addActionListener(this);
        btn_dragbar.addActionListener(this);
        btn_minimize.addActionListener(this);
        btn_close.addActionListener(this);
        btn_setting.setBounds(5,5,20,20);
        btn_dragbar.setBounds(140,5,20,20);
        btn_minimize.setBounds(250,5,20,20);
        btn_close.setBounds(275,5,20,20);

        // JPanel
        contentPanel.add(btn_setting);
        contentPanel.add(btn_dragbar);
        contentPanel.add(btn_minimize);
        contentPanel.add(btn_close);
        panel.setBackground(new Color(0,0,0,0));
        panel.setLayout(flowLayout);
        contentPanel.add(panel);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btn_setting.equals(e.getSource())) {
            System.out.println("setting");
        }else if(btn_dragbar.equals(e.getSource())){
            System.out.println("dragbar");
        }else if(btn_minimize.equals(e.getSource())){
            System.out.println("minimize");
        }else if(btn_close.equals(e.getSource())){
            System.out.println("close");
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
