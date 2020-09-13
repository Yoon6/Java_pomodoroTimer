import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class settingFrame extends defaultFrame implements ActionListener{

    JButton btn_close;
    ImageIcon img_close = new ImageIcon("res/baseline_clear_white_36dp.png");
    Image tmp_close = img_close.getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH);

    settingPanel sp = new settingPanel(); // 체크박스 패널
    JPanel panel = new JPanel(); // 닫기 버튼 패널

    settingFrame(){
        super("Settings");
        setSize(300,200);
        setBackground(new Color(0,0,0,122));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 닫으면 안보이게
        setLayout(new FlowLayout());

        // 초기 위치 설정
        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙

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

    // 버튼 리스너
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== btn_close){
            mainFrame.settingIsOpened=false; // 세팅 패널이 열렸는지.
            dispose(); // 안보이게
        }
    }

    // 버튼 투명
    public void createButton(JButton btn){
        btn.setPreferredSize(new Dimension(24,24));
        btn.setBackground(Color.red);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
    }


}
