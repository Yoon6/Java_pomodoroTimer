package ProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;

public class CustomPanel extends JPanel {

    int x=0,y=0;

    NumberFormat format;

    Graphics2D g2D;

    String timeSet = " ";
    long progress = 60*1000;
    int setTime = 1;


    public void UpdateProgress(long progress, int setTime){
        this.setTime=setTime;
        this.progress=progress;
        // 시간표시 포맷설정
        format = NumberFormat.getNumberInstance();
        format.setMinimumIntegerDigits(2);

        int minutes = (int) (progress / 60000); // 밀리초단위에서 분표시
        int seconds = (int) ((progress % 60000) / 1000); // 초단위 표시
        timeSet=format.format(minutes) + ":"
                + format.format(seconds); // 시간 표시


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2D = (Graphics2D)g;
        // 라인 부드럽게
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 보이게 하기?
        g2D.translate(this.getWidth()/2, this.getHeight()/2);
        // 시계반대방향으로 움직이기때문에 rotate(회전)를 설정해준다.
        // x축방향에서 시작해서 시계방향으로 270도를 돌리는 것이다.
        g2D.rotate(Math.toRadians(270));
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        // 가리는 원
        Ellipse2D circle = new Ellipse2D.Float(0,0,65,65);
        Ellipse2D base_circle = new Ellipse2D.Float(0,0,85,85);
        arc.setFrameFromCenter(new Point(0,0),new Point(80,80));
        circle.setFrameFromCenter(new Point(0,0),new Point(70,70));
        base_circle.setFrameFromCenter(new Point(0,0),new Point(85,85));
        // 시작 각도
        arc.setAngleStart(1);
        // 범위 ; -:반대로
        double angle = (double)((progress/1000)*(6.0/setTime));
        arc.setAngleExtent(angle);// 360/100

        // 그림
        g2D.setColor(new Color(70, 70, 70));
        g2D.draw(base_circle);
        g2D.fill(base_circle);
        g2D.setColor(new Color(251, 103, 98));
        g2D.draw(arc);
        g2D.fill(arc);
        g2D.setColor(new Color(70, 70, 70));
        g2D.draw(circle);
        g2D.fill(circle);

        // 글자
        g2D.setColor(new Color(230, 230, 230));
        g2D.rotate(Math.toRadians(90));
        g.setFont(new Font("Verdana",Font.PLAIN,35));
        FontMetrics fm = g2D.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(timeSet,g);
        x = (0-(int)r.getWidth())/2; // 글자 폭의 반
        y = (0-(int)r.getHeight())/2+fm.getAscent(); // 글자 높이의 반 + y축값
        g2D.drawString(timeSet,x,y);
        if(progress<=0){
            timeSet=" ";
        }
        g2D.drawString(timeSet,x,y);


    }
}
