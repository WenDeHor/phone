//package com.example.phone;
//
////import javafx.embed.swing.JFXPanel;
////import javafx.scene.media.Media;
////import javafx.scene.media.MediaPlayer;
//import org.springframework.stereotype.Component;
//
//import javax.sound.sampled.*;
//import java.io.File;
//import java.io.IOException;
//
//@Component
//public class ListeningWav {
//    static String URL = "src\\main\\resources\\RecordAudio.wav";
////
//    public void startListening()  {
//        try {
//            File soundFile = new File(URL); //Звуковой файл
//
//            //Получаем AudioInputStream
//            //Вот тут могут полететь IOException и UnsupportedAudioFileException
//            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
//
//            //Получаем реализацию интерфейса Clip
//            //Может выкинуть LineUnavailableException
//            Clip clip = AudioSystem.getClip();
//
//            //Загружаем наш звуковой поток в Clip
//            //Может выкинуть IOException и LineUnavailableException
//            clip.open(ais);
//
//            clip.setFramePosition(0); //устанавливаем указатель на старт
//            clip.start(); //Поехали!!!
//
//            //Если не запущено других потоков, то стоит подождать, пока клип не закончится
//            //В GUI-приложениях следующие 3 строчки не понадобятся
//            Thread.sleep(clip.getMicrosecondLength()/1000);
//            clip.stop(); //Останавливаем
//            clip.close(); //Закрываем
//        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
//            exc.printStackTrace();
//        } catch (InterruptedException exc) {}
//
////        new JFXPanel();
////        String u = new File(URL).toURI().toString();
////        new MediaPlayer(new Media(u)).play();
//    }
//}
