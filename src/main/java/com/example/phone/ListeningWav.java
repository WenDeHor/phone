package com.example.phone;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ListeningWav {
    static String URL = "src\\main\\resources\\RecordAudio.wav";
//
    public void startListening()  {
        new JFXPanel();
        String u = new File(URL).toURI().toString();
        new MediaPlayer(new Media(u)).play();
    }
}
