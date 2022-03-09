package com.example.phone;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.sun.media.BasicPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.sound.sampled.*;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javazoom.jl.player.Player;
import org.springframework.stereotype.Component;
import sun.audio.AudioStream;

@Component
public class ListeningWav {
    static String URL = "src\\main\\resources\\RecordAudio.wav";

    public void startListening()  {
        new JFXPanel();
        String u = new File(URL).toURI().toString();
        new MediaPlayer(new Media(u)).play();
    }
}
