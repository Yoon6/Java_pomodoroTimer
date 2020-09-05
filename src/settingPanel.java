import javax.swing.*;
import java.awt.event.ItemEvent;

public class settingPanel extends JPanel {
    JComboBox<Integer> comboBox;
    static int ttime = 19;


    settingPanel(){
        comboBox = new JComboBox<Integer>();
        for(int i=1; i<=60; i++){
            comboBox.addItem(Integer.valueOf(i));
        }
        comboBox.addItemListener(this::itemStateChanged);
        comboBox.setSelectedIndex(ttime);

        add(comboBox);
    }

    public void itemStateChanged(ItemEvent ie) {
        if(!mainFrame.isPlay) {
            ttime = (int) comboBox.getSelectedItem() - 1;
            mainFrame.setSetTime(ttime + 1);
        }
    }
}
