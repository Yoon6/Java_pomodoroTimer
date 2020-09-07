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

    settingPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;

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
        gbc.gridx=1;
        gbc.gridy=0;
        add(comboBox, gbc);

        auto = new JCheckBox("Automatically restart");
        auto.setBackground(new Color(0,0,0,0));
        auto.setFont(new Font("Arial",Font.PLAIN,20));
        auto.setForeground(Color.WHITE);
        gbc.gridx=0;
        gbc.gridy=1;
        add(auto, gbc);

        routine = new JCheckBox("Use routine");
        routine.setBackground(new Color(0,0,0,0));
        routine.setFont(new Font("Arial",Font.PLAIN,20));
        routine.setForeground(Color.WHITE);
        gbc.gridx=0;
        gbc.gridy=2;
        add(routine, gbc);

        sound = new JCheckBox("Sound on");
        sound.setBackground(new Color(0,0,0,0));
        sound.setFont(new Font("Arial",Font.PLAIN,20));
        sound.setForeground(Color.WHITE);
        gbc.gridx=0;
        gbc.gridy=3;
        add(sound, gbc);

    }

    public void itemStateChanged(ItemEvent ie) {
        if(!mainFrame.isPlay) {
            ttime = (int) comboBox.getSelectedItem() - 1;
            mainFrame.setSetTime(ttime + 1);
        }
    }
}
