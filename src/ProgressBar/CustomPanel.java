package ProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class CustomPanel extends JPanel {
    int progress = 0;
    public void UpdateProgress(int progress){
        this.progress=progress;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D)g;
        // 보이게 하기?
        g2D.translate(this.getWidth()/2, this.getHeight()/2);
        // 시계반대방향으로 움직이기때문에 rotate(회전)를 설정해준다.
        // x축방향에서 시작해서 시계방향으로 270도를 돌리는 것이다.
        g2D.rotate(Math.toRadians(270));
        Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
        arc.setFrameFromCenter(new Point(0,0),new Point(120,120));
        // 시작 각도
        arc.setAngleStart(1);
        // 범위 ; -:반대로
        arc.setAngleExtent(-progress*(360/100));
        g2D.setColor(Color.red);
        g2D.draw(arc);
        g2D.fill(arc);
    }
}
