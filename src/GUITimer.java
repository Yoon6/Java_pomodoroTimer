import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GUITimer extends JFrame implements ItemListener {

    private static final long serialVersionUID = 5924880907001755076L;

    JLabel jltime;
    JLabel jl;
    JComboBox<Integer> jcb;
    JButton jbt;
    JButton jbt2;
    NumberFormat format;

    public Timer timer;
    public long initial;
    public long ttime2;
    public String ttime;
    public long remaining;

    public GUITimer() {

        // 시간 패널
        JPanel timePanel = new JPanel();
        timePanel.setForeground(Color.RED);

        // 시간 나타내는 글자 설정
        jltime = new JLabel(" ");
        jltime.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        // 글자 색
        jltime.setForeground(Color.RED);
        //jltime.setBackground(Color.BLACK);
        jltime.setOpaque(true);
        jltime.setFont(new Font("Arial", Font.BOLD, 96));

        timePanel.add(jltime);

        // 콤보박스, 버튼 패널
        JPanel jp1 = new JPanel();
        jp1.setLayout(new FlowLayout());

        // 글자
        jl = new JLabel("TOTAL TIME (minutes):");
        jp1.add(jl);

        // 콤보박스
        jcb = new JComboBox<Integer>();
        for (int i = 15; i > 0; i--) {
            jcb.addItem(Integer.valueOf(i));
        }
        jcb.setSelectedIndex(0);
        ttime = "15"; // 콤보박스 초기값
        jp1.add(jcb);

        // 시작버튼
        jbt = new JButton("START");
        jp1.add(jbt);

        // 리셋버튼
        jbt2 = new JButton("RESET");
        jp1.add(jbt2);

        // 컨테이너에 추가
        getContentPane().add(jp1, BorderLayout.NORTH);
        getContentPane().add(timePanel, BorderLayout.CENTER);

        // 버튼 리스너
        Event e = new Event();
        jbt.addActionListener(e);
        jbt2.addActionListener(e);

        // 콤보박스 리스너
        jcb.addItemListener(this);

        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("LARC Moot Countdown Timer");
        pack();
        // 이 윈도우가 다음번에 가시로 되었을 경우에 네이티브의 윈도우 시스템의 디폴트의 장소에 표시하는지, 현재의 장소 (getLocation에 의해 리턴된 것)에 표시하는지를 설정합니다.
        setLocationByPlatform(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUITimer();
            }

        });
    }

    // this method will run when user presses the start button
    void updateDisplay() {

        Timeclass tc = new Timeclass();
        timer = new Timer(1000, tc);
        initial = System.currentTimeMillis();
        timer.start();
    }

    // code for what happens when user presses the start or reset button
    public class Event implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String bname = e.getActionCommand();
            if (bname.equals("START")) { // start 눌렀을 때
                updateDisplay();
            } else { // reset
                jltime.setText(" "); // 시간 글자 표시 초기화
                timer.stop(); // Timer 클래스의 stop()메소드
                remaining = convertTime();
            }
        }
    }

    // code that is invoked by swing timer for every second passed
    public class Timeclass implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            remaining = convertTime();
            long current = System.currentTimeMillis();
            long elapsed = current - initial;
            remaining -= elapsed;
            // initial = current;

            format = NumberFormat.getNumberInstance();
            format.setMinimumIntegerDigits(2);

            if (remaining < 0)
                remaining = (long) 0;
            int minutes = (int) (remaining / 60000);
            int seconds = (int) ((remaining % 60000) / 1000);
            jltime.setText(format.format(minutes) + ":"
                    + format.format(seconds));

            if (remaining == 0) {
                jltime.setText("Stop");
                timer.stop();
            }
        }
    }

    // get the number of minutes chosen by the user and activate convertTime
    // method
    public void itemStateChanged(ItemEvent ie) {

        ttime = (String) jcb.getSelectedItem().toString(); // 콤보박스에서 선택한 분(1~15)를 String으로 받아옴
        convertTime();
    }

    // first need to convert no. of minutes from string to long.
    // then need to convert that to milliseconds.
    public long convertTime() {

        ttime2 = Long.parseLong(ttime); // String -> long 형변환
        long converted = (ttime2 * 60 * 1000) + 1000; // ttime2*60초+1초, 분 단위를 밀리초 단위로 변환환
        return converted;
    }
}