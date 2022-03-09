package com.example.phone;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final SoundRecorder soundRecorder;
    private final ListeningWav listeningWav;

    public MainController(SoundRecorder soundRecorder, ListeningWav listeningWav) {
        this.soundRecorder = soundRecorder;
        this.listeningWav = listeningWav;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "mine-page";
    }

    @GetMapping("/call")
    public String call(Model model) {
        setRecord();
        return "redirect:/";
    }
    @GetMapping("/voice")
    public String voice(Model model) {
        getVoice();
        return "redirect:/";
    }

    @Async
    public void getVoice() {
        listeningWav.startListening();
    }

    @Async
    public void setRecord() {
        soundRecorder.startRecord();
    }
}


