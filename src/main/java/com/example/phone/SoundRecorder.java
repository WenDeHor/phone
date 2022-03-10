//package com.example.phone;
//
//import lombok.SneakyThrows;
//import org.springframework.stereotype.Component;
//
//import javax.sound.sampled.*;
//import java.io.*;
//
//@Component
//public class SoundRecorder {
//    final long RECORD_TIME = 10000;  // 1 minute
//    File wavFile = new File("src\\main\\resources\\RecordAudio.wav");
//    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
//    TargetDataLine line;
//
//    AudioFormat getAudioFormat() {
//        float sampleRate = 16000;
//        int sampleSizeInBits = 8;
//        int channels = 2;
//        boolean signed = true;
//        boolean bigEndian = true;
//        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
//        return format;
//    }
//
//    void start() {
//        try {
//            AudioFormat format = getAudioFormat();
//            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//            if (!AudioSystem.isLineSupported(info)) {
//                System.out.println("Line not supported");
//                System.exit(0);
//            }
//            line = (TargetDataLine) AudioSystem.getLine(info);
//            line.open(format);
//            line.start();   // start capturing
//            System.out.println("Start capturing...");
//            AudioInputStream ais = new AudioInputStream(line);
//            System.out.println("Start recording...");
//            AudioSystem.write(ais, fileType, wavFile);
//        } catch (LineUnavailableException ex) {
//            ex.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
//
//    public void startRecord() {
//        final SoundRecorder recorder = new SoundRecorder();
//        Thread stopper = new Thread(new Runnable() {
//            @SneakyThrows
//            public void run() {
//                try {
//                    Thread.sleep(RECORD_TIME);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
//                recorder.finish2();
//
//            }
//        });
//        stopper.start();
//        recorder.start();
//    }
//
//    public void finish2() {
//        line.stop();
//        line.close();
//        System.out.println("Finished");
//    }
//}