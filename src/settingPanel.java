import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class settingPanel extends JPanel{
    JComboBox<Integer> comboBox;
    static int ttime = 19;
    JLabel label_combo;
    JCheckBox auto;
    JCheckBox routine;
    JCheckBox sound;
    JCheckBox break_screen;
    static boolean autoChecked=true;
    static boolean breakChecked=true;
    static boolean soundChecked=true;

    GridBagLayout grid = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    settingPanel(){
        setLayout(grid);

        init();

        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=1.0;
        gbc.weighty=1.0;

        make(label_combo,0,0,3,1);
        make(comboBox,1,0,1,1);
        make(auto,0,1,4,1);
        make(sound,0,2,4,1);
        make(break_screen,0,3,4,1);
        make(routine,0,4,4,1);

        add(label_combo);
        add(comboBox);
        add(auto);
        add(sound);
        add(break_screen);

    }

    public void itemStateChanged(ItemEvent ie) {
        if(!mainFrame.isPlay) {
            ttime = (int) comboBox.getSelectedItem() - 1;
            mainFrame.setSetTime(ttime + 1);
        }
    }

    public void init(){
        label_combo = new JLabel("Time Setting : ");
        label_combo.setFont(new Font("Arial",Font.PLAIN,20));
        label_combo.setForeground(Color.WHITE);
        add(label_combo, gbc);
        comboBox = new JComboBox<Integer>();
        for(int i=1; i<=60; i++){
            comboBox.addItem(Integer.valueOf(i));
        }
        comboBox.addItemListener(this::itemStateChanged);
        comboBox.setSelectedIndex(ttime);
        comboBox.setEditable(true);

        // 체크박스 글자, 폰트, 배경, 색 설정
        auto = new JCheckBox("Automatically restart");
        auto.setBackground(new Color(0,0,0,0));
        auto.setFont(new Font("Arial",Font.PLAIN,20));
        auto.setForeground(Color.WHITE);

        routine = new JCheckBox("Use routine");
        routine.setBackground(new Color(0,0,0,0));
        routine.setFont(new Font("Arial",Font.PLAIN,20));
        routine.setForeground(Color.WHITE);

        sound = new JCheckBox("Use sound");
        sound.setBackground(new Color(0,0,0,0));
        sound.setFont(new Font("Arial",Font.PLAIN,20));
        sound.setForeground(Color.WHITE);

        break_screen = new JCheckBox("Use break screen");
        break_screen.setBackground(new Color(0,0,0,0));
        break_screen.setFont(new Font("Arial",Font.PLAIN,20));
        break_screen.setForeground(Color.WHITE);


        // 체크박스 유지를 위한 코드
        auto.setSelected(true);
        if(!autoChecked)
            auto.setSelected(false);

        break_screen.setSelected(true);
        if(!breakChecked)
            break_screen.setSelected(false);

        sound.setSelected(true);
        if(!soundChecked)
            sound.setSelected(false);

        // 체크박스 리스너
        auto.addItemListener(new ItemListener() { // 오토플레이
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange()==ItemEvent.SELECTED){ // 체크가 됐으면
                    System.out.println("check");
                    mainFrame.autoPlay=true;
                    autoChecked=true; // 체크박스 체크 여부
                }else if(e.getStateChange()==ItemEvent.DESELECTED){
                    System.out.println("uncheck");
                    mainFrame.autoPlay=false;
                    autoChecked=false;
                }
            }
        });
        break_screen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println("check");
                    mainFrame.break_screenCheck=true;
                    breakChecked=true;
                }else if(e.getStateChange()==ItemEvent.DESELECTED){
                    System.out.println("uncheck");
                    mainFrame.break_screenCheck=false;
                    breakChecked=false;
                }
            }
        });
        sound.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if(e.getStateChange()==ItemEvent.SELECTED){
                    System.out.println("check");
                    mainFrame.soundCheck=true;
                    soundChecked=true;
                }else if(e.getStateChange()==ItemEvent.DESELECTED){
                    System.out.println("uncheck");
                    mainFrame.soundCheck=false;
                    soundChecked=false;
                }
            }
        });
    }

    public void make(JComponent c, int x, int y, int w, int h) {

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;

        grid.setConstraints(c, gbc);
        // GridBagLayout의 GridBagConstraints의 set하는 방법
    }
}
