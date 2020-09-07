import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class settingPanel extends JPanel {
    JComboBox<Integer> comboBox;
    static int ttime = 19;
    JLabel label_combo;
    JCheckBox auto;
    JCheckBox routine;
    JCheckBox sound;
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
        make(routine,0,3,4,1);

        add(label_combo);
        add(comboBox);
        add(auto);
        add(sound);
        add(routine);

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

        auto = new JCheckBox("Automatically restart");
        auto.setBackground(new Color(0,0,0,0));
        auto.setFont(new Font("Arial",Font.PLAIN,20));
        auto.setForeground(Color.WHITE);

        routine = new JCheckBox("Use routine");
        routine.setBackground(new Color(0,0,0,0));
        routine.setFont(new Font("Arial",Font.PLAIN,20));
        routine.setForeground(Color.WHITE);

        sound = new JCheckBox("Sound on");
        sound.setBackground(new Color(0,0,0,0));
        sound.setFont(new Font("Arial",Font.PLAIN,20));
        sound.setForeground(Color.WHITE);
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
