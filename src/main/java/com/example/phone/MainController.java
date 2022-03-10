package com.example.phone;

import com.example.phone.model.Phone;
import com.example.phone.repository.PhoneRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @PostMapping("/call")
    public String call(Model model, MultipartFile file) throws IOException {
//        Phone phone = new Phone();
//        phone.setVolume(file.getBytes());
//        phoneRepository.save(phone);
        setRecord();
        return "redirect:/";
    }

    @GetMapping("/voice")
    public String voice(Model model) {
        getVoice();
        return "redirect:/";
    }


    public void getVoice() {
        listeningWav.startListening();
    }


    public void setRecord() {
        soundRecorder.startRecord();
    }
}


