import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class mainFrame extends defaultFrame implements ActionListener{

    public static int getSetTime() {
        return setTime;
    }
    public static void setSetTime(int setTime) {
        mainFrame.setTime = setTime;
    }
    private static int setTime = 20;

    static boolean settingIsOpened=false;
    static boolean isPlay=false;
    boolean Replay = false;
    static boolean autoPlay=true; // 설정 체크
    static boolean break_time_out=false; // esc입력
    static boolean break_screenCheck=true; // 설정 체크

    long process1;

    // Button
    JButton btn_close;
    JButton btn_minimize;
    JButton btn_setting;
    JButton btn_Play;
    JButton btn_pause;
    JButton btn_resume;
    JButton btn_replay;

    ImageIcon img_pause = new ImageIcon("res/baseline_pause_white_48dp.png");
    Image tmp_pause = img_pause.getImage().getScaledInstance(48,48,Image.SCALE_SMOOTH);

    ImageIcon img_resume = new ImageIcon("res/baseline_play_arrow_white_48dp.png");
    Image tmp_resume = img_resume.getImage().getScaledInstance(36,36,Image.SCALE_SMOOTH);

    ImageIcon img_replay = new ImageIcon("res/baseline_replay_white_48dp.png");
    Image tmp_replay = img_replay.getImage().getScaledInstance(36,36,Image.SCALE_SMOOTH);

    // Image
    ImageIcon img_setting = new ImageIcon("res/baseline_settings_white_18dp.png");
    ImageIcon img_minimize = new ImageIcon("res/outline_minimize_white_18dp.png");
    ImageIcon img_close = new ImageIcon("res/outline_clear_white_18dp.png");
    ImageIcon img_play = new ImageIcon("res/baseline_play_arrow_white_48dp.png");
    Image tmp_set = img_setting.getImage().getScaledInstance(18,18,Image.SCALE_SMOOTH);
    Image tmp_minimize = img_minimize.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
    Image tmp_close = img_close.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
    Image tmp_play = img_play.getImage().getScaledInstance(48,48,Image.SCALE_SMOOTH);

    // Panel
    Container contentPanel = this.getContentPane();

    public mainPanel circleBar_panel = new mainPanel();

    public static Thread th_main;
    restFrame rf;

    public mainFrame(String title){
        super(title);
        init();

        Dimension frameSize = this.getSize(); // 프레임 사이즈
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 사이즈
        this.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2); // 화면 중앙

        contentPanel.add(btn_setting);
        contentPanel.add(btn_close);
        contentPanel.add(btn_Play);
        contentPanel.add(btn_pause);
        btn_pause.setVisible(isPlay);
        contentPanel.add(btn_resume);
        btn_resume.setVisible(isPlay);
        contentPanel.add(btn_replay);
        btn_replay.setVisible(isPlay);
        contentPanel.add(circleBar_panel);


        setVisible(true);
    }

    public void init(){
        // JFrame
        setSize(200,200);
        setBackground(new Color(0,0,0,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JButton
        btn_setting = new JButton(new ImageIcon(tmp_set));
        btn_minimize = new JButton(new ImageIcon(tmp_minimize));
        btn_close = new JButton(new ImageIcon(tmp_close));
        btn_Play = new JButton(new ImageIcon(tmp_play));
        btn_pause = new JButton(new ImageIcon(tmp_pause));
        btn_pause.setBackground(new Color(70, 70, 70));
        btn_pause.setBorderPainted(false); // 버튼 테두리
        btn_pause.setFocusPainted(false); // 포커스
        btn_resume = new JButton(new ImageIcon(tmp_resume));
        //btn_resume.setBackground(new Color(70, 70, 70));
        //btn_resume.setBorderPainted(false); // 버튼 테두리
        //btn_resume.setFocusPainted(false); // 포커스
        btn_replay = new JButton(new ImageIcon(tmp_replay));
        //btn_replay.setBackground(new Color(70, 70, 70));
        //btn_replay.setBorderPainted(false); // 버튼 테두리
        //btn_replay.setFocusPainted(false); // 포커스

        createButton(btn_setting);
        createButton(btn_minimize);
        createButton(btn_close);
        createButton(btn_Play);
        createButton(btn_resume);
        createButton(btn_replay);

        // listener
        btn_setting.addActionListener(this);
        btn_minimize.addActionListener(this);
        btn_close.addActionListener(this);
        btn_Play.addActionListener(this);
        btn_pause.addActionListener(this);
        btn_resume.addActionListener(this);
        btn_replay.addActionListener(this);
        // set location and size
        btn_setting.setBounds(90,133,20,20);
        btn_minimize.setBounds(180,5,20,20);
        btn_close.setBounds(90,50,20,20);
        btn_Play.setBounds(76,77,48,48);
        btn_pause.setBounds(40,77,120,48);
        btn_resume.setBounds(40,77,60,48);
        btn_replay.setBounds(100,77,60,48);

        circleBar_panel.setBackground(new Color(0,128,128,0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btn_setting.equals(e.getSource())) {
            System.out.println("setting");
            if(!settingIsOpened){
                new settingFrame();
                settingIsOpened=true;
            }
        }else if(btn_minimize.equals(e.getSource())){
            System.out.println("minimize");
            // minimize
            setState(Frame.ICONIFIED);
        }else if(btn_close.equals(e.getSource())){
            System.out.println("close");
            // close
            System.exit(0);
        }else if(btn_Play.equals(e.getSource())){
            Replay=false;
            isPlay=true;
            th_main = new Thread(new Runnable() {
                @Override
                public void run() {
                    process1 = setTime*60*1000;
                    while(!Replay){
                        if(process1<0)
                            break;

                        circleBar_panel.UpdateProgress(process1,setTime);
                        circleBar_panel.repaint();
                        try {
                            Thread.sleep(1000);

                            if (process1 == 0) {
                                if(break_screenCheck) {
                                    rf = new restFrame();
                                    int i = 10;
                                    while (!break_time_out) {
                                        if (i <= 0) break;
                                        rf.time.setText(i + "");
                                        Thread.sleep(1000);
                                        i--;
                                    }
                                    rf.dispose();
                                }
                                break_time_out=false;
                                if (autoPlay) {
                                    process1 = setTime * 60 * 1000+1000;
                                } else {
                                    circleBar_panel.setTime = 1;
                                    circleBar_panel.progress = 60 * 1000;
                                    circleBar_panel.repaint();
                                    btn_Play.setVisible(!isPlay);
                                    btn_pause.setVisible(isPlay);
                                }
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        process1-=1000;
                    }
                }
            });
            th_main.start();
            btn_Play.setVisible(!isPlay);
            btn_pause.setVisible(isPlay);
        }
        else if(btn_pause.equals(e.getSource())){
            System.out.println("pause");
            th_main.suspend();

            circleBar_panel.UpdateProgress(process1,setTime);
            mainPanel.timeSet=" ";
            circleBar_panel.repaint();

            btn_pause.setVisible(false);
            btn_resume.setVisible(true);
            btn_replay.setVisible(true);

        }else if(btn_resume.equals(e.getSource())){
            System.out.println("resume");
            th_main.resume();

            btn_pause.setVisible(true);
            btn_resume.setVisible(false);
            btn_replay.setVisible(false);
        }else if(btn_replay.equals(e.getSource())){
            // 처음으로 돌아가서 다시 시작.
            process1 = setTime*60*1000;
            circleBar_panel.UpdateProgress(process1,setTime);
            mainPanel.timeSet=" ";
            circleBar_panel.repaint();

            Replay=true;
            th_main.resume();

            btn_pause.setVisible(true);
            btn_resume.setVisible(false);
            btn_replay.setVisible(false);
            // 시작 상태로 만들어야함
            isPlay=false;
            btn_Play.setVisible(true);
            btn_pause.setVisible(false);
        }
    }

    public void createButton(JButton btn){
        //btn.setPreferredSize(new Dimension(20,20));
        btn.setBackground(Color.red);
        btn.setBorderPainted(false); // 버튼 테두리
        btn.setFocusPainted(false); // 포커스
        btn.setContentAreaFilled(false); // 영역표시
    }

    }
