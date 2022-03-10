package com.example.phone;

import com.example.phone.model.Phone;
import com.example.phone.repository.PhoneRepository;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class MainController {
    private final SoundRecorder soundRecorder;
    private final ListeningWav listeningWav;
    private final PhoneRepository phoneRepository;

    public MainController(SoundRecorder soundRecorder, ListeningWav listeningWav, PhoneRepository phoneRepository) {
        this.soundRecorder = soundRecorder;
        this.listeningWav = listeningWav;
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "mine-page";
    }

    //    @PostMapping("/call")
//    public String call(Model model, MultipartFile file) throws IOException {
//        Phone phone = new Phone();
////        phone.setId(1);
//        phone.setVolume(file.getBytes());
//        phoneRepository.save(phone);
////        setRecord();
//        return "redirect:/";
//    }
    TargetDataLine microphone;
    SourceDataLine speakers;

    @GetMapping("/call")
    public String call2() {
        for (int i = 0; i < 100; i++) {
            AudioFormat format = new AudioFormat(8000.0f, 16, 2, true, true);

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
        return "redirect:/";
    }

    @GetMapping("/stop")
    public String showUserPagePhoto() {
        speakers.close();
        microphone.close();
        return "redirect:/";
    }
}


