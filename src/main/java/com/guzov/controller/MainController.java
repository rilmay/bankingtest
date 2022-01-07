package com.guzov.controller;

import com.guzov.domain.Message;
import com.guzov.repostory.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(
    ) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public RedirectView add(
            @RequestParam String text,
            @RequestParam String tag
    ) {
        Message message = new Message(text, tag);
        messageRepo.save(message);
        return new RedirectView("/main");
    }

    @PostMapping("filter")
    public String filter(@RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> messages;
        if (tag == null || tag.isEmpty()) {
            messages = messageRepo.findAll();
        } else {
            messages= messageRepo.findByTag(tag);
        }
        model.put("messages", messages);
        return "main";
    }
}
