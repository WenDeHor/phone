package com.example.phone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;

@Controller
public class MainController {
    private TargetDataLine microphone;
    private SourceDataLine speakers;

    @GetMapping("/")
    public String index(Model model) {
        return "mine-page";
    }

    @GetMapping("/call")
    public void call2() {
        for (int i = 0; i < 100; i++) {
            AudioFormat format = new AudioFormat(8000.0f, 8, 2, true, true);

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
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
                speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                speakers.open(format);
                speakers.start();
                while (bytesRead < 100000) {
                    numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
                    bytesRead += numBytesRead;
                    // write the mic data to a stream for use later
                    out.write(data, 0, numBytesRead);
                    // write mic data to stream for immediate playback
                    speakers.write(data, 0, numBytesRead);
                }
                speakers.drain();
                speakers.close();
                microphone.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        return "redirect:/";
    }

    @GetMapping("/stop")
    public void showUserPagePhoto() {
        speakers.close();
        microphone.close();
//        return "redirect:/";
    }
}


