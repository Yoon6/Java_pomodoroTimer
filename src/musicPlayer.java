import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import sun.audio.*;

import javax.sound.sampled.*;
import javax.swing.*;

public class musicPlayer {
    public void play(String path){
        File bgm;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        bgm = new File(path); // 사용시에는 개별 폴더로 변경할 것

        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

        } catch (Exception e) {
            System.out.println("err : " + e);
        }
    }

}

