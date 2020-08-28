package ProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressBarDemo extends JFrame{
    private JButton button1;
    Container contentPanel = this.getContentPane();
    private JPanel jpanel = new CustomPanel();
    private JPanel fir_panel = new JPanel();

    public ProgressBarDemo(){
        super("ProgressBar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,500);
        button1 = new JButton("Start");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click!");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int num=1; num<100; num++){
                            //jpanel.UpdateProgress(num);
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
        });

        jpanel.add(button1);
        add(jpanel);
        setVisible(true);
    }

}
