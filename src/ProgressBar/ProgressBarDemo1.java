package ProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProgressBarDemo1 extends JFrame {
    private JButton button1;
    Container contentPanel = this.getContentPane();
    private CustomPanel jpanel = new CustomPanel();
    private JPanel fir_panel = new JPanel();

    public ProgressBarDemo1(){
        super("ProgressBar");
        setSize(600,500);
        button1 = new JButton("Start");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpanel.add(button1);
        add(jpanel);
        setVisible(true);
    }

    public void buttonActionPerformed(ActionEvent evt){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int num=1; num<100; num++){
                    jpanel.UpdateProgress(num);
                    jpanel.repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
