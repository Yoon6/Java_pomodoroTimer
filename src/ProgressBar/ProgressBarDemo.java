package ProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarDemo extends JFrame implements ActionListener {
    private JButton button1;
    Container contentPanel = this.getContentPane();
    private CustomPanel jpanel = new CustomPanel();
    private JPanel fir_panel = new JPanel();

    public ProgressBarDemo(){
        super("ProgressBar");
        setSize(600,500);
        button1 = new JButton("Start");
        button1.addActionListener(this::actionPerformed);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpanel.add(button1);
        add(jpanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int num=100; num>=0; num--){
                    jpanel.UpdateProgress(num);
                    jpanel.repaint();
                    try {
                        Thread.sleep(50);
                        if(num==0){
                            jpanel.UpdateProgress(100);
                            jpanel.repaint();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
