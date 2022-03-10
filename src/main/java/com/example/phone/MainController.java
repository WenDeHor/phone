package com.example.phone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.sound.sampled.*;
//import java.io.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

@Controller
public class MainController {
    private TargetDataLine microphone;
    private SourceDataLine speakers;

    @GetMapping("/")
    public String index(Model model) {
        return "mine-page";
    }

    @GetMapping("/call")
    public String call2() {

        AudioFormat format = new AudioFormat(8000.0f, 16, 2, true, true);
//        TargetDataLine microphone;
        AudioInputStream audioInputStream;
//        SourceDataLine speakers;
        try {
            microphone = AudioSystem.getTargetDataLine(format);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            microphone = (TargetDataLine) AudioSystem.getLine(info);
            microphone.open(format);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            int CHUNK_SIZE = 1024;
            byte[] data = new byte[microphone.getBufferSize() / 5];
            microphone.start();

            int bytesRead = 0;

            try {
                while (bytesRead < 100000) { // Just so I can test if recording
                    // my mic works...
                    numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                    bytesRead = bytesRead + numBytesRead;
//                    System.out.println(bytesRead);
                    out.write(data, 0, numBytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] audioData = out.toByteArray();
            // Get an input stream on the byte array
            // containing the data
            InputStream byteArrayInputStream = new ByteArrayInputStream(
                    audioData);
            audioInputStream = new AudioInputStream(byteArrayInputStream,format, audioData.length / format.getFrameSize());
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            speakers.open(format);
            speakers.start();
            int cnt = 0;
            byte[] tempBuffer = new byte[10000];
            try {
                while ((cnt = audioInputStream.read(tempBuffer, 0,tempBuffer.length)) != -1) {
                    if (cnt > 0) {
                        // Write data to the internal buffer of
                        // the data line where it will be
                        // delivered to the speaker.
                        speakers.write(tempBuffer, 0, cnt);
                    }// end if
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Block and wait for internal buffer of the
            // data line to empty.
            speakers.drain();
            speakers.close();
            microphone.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < 100; i++) {
//            AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
//            try {
//                microphone = AudioSystem.getTargetDataLine(format);
//                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
//                microphone = (TargetDataLine) AudioSystem.getLine(info);
//                microphone.open(format);
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                int numBytesRead;
//                int CHUNK_SIZE = 1024;
//                byte[] data = new byte[microphone.getBufferSize() / 5];
//                microphone.start();
//                int bytesRead = 0;
//                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
//                speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
//                speakers.open(format);
//                speakers.start();
//                while (bytesRead < 50000) {
//                    numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
//                    bytesRead += numBytesRead;
//                    // write the mic data to a stream for use later
//                    out.write(data, 0, numBytesRead);
//                    // write mic data to stream for immediate playback
//                    speakers.write(data, 0, numBytesRead);
//                }
//            } catch (LineUnavailableException e) {
//                e.printStackTrace();
//            }
//            speakers.drain();
//            speakers.close();
//            speakers.flush();
//            microphone.close();
//            microphone.flush();
//        }
        return "redirect:/";
    }

    @GetMapping("/stop")
    public String showUserPagePhoto() {
        speakers.close();
        microphone.close();
        return "redirect:/";
    }
}


