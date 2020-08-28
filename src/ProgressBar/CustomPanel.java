package ProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class CustomPanel extends JPanel {
    int progress = 100;
    public void UpdateProgress(int progress){
        this.progress=progress;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g;
        // 라인 부드럽게
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 보이게 하기?
        g2D.translate(this.getWidth()/2, this.getHeight()/2);
        // 시계반대방향으로 움직이기때문에 rotate(회전)를 설정해준다.
        // x축방향에서 시작해서 시계방향으로 270도를 돌리는 것이다.
        g2D.rotate(Math.toRadians(270));
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        // 가리는 원
        Ellipse2D circle = new Ellipse2D.Float(0,0,110,110);
        arc.setFrameFromCenter(new Point(0,0),new Point(120,120));
        circle.setFrameFromCenter(new Point(0,0),new Point(110,110));
        // 시작 각도
        arc.setAngleStart(1);
        // 범위 ; -:반대로
        arc.setAngleExtent(progress*3.6);// 360/100

        // 그림
        g2D.setColor(Color.red);
        g2D.draw(arc);
        g2D.fill(arc);
        g2D.setColor(Color.white);
        g2D.draw(circle);
        g2D.fill(circle);
        // 글자
        g2D.setColor(Color.DARK_GRAY);
        g2D.rotate(Math.toRadians(90));
        g.setFont(new Font("Verdana",Font.PLAIN,50));
        FontMetrics fm = g2D.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(progress+"%",g);
        int x = (0-(int)r.getWidth())/2; // 글자 폭의 반
        int y = (0-(int)r.getHeight())/2+fm.getAscent(); // 글자 높이의 반 + y축값
        g2D.drawString(progress+"%",x,y);
    }
}
