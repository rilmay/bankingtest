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
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "main";
    }

    @PostMapping(value = "/")
    public RedirectView add(
            @RequestParam String text,
            @RequestParam String tag
    ) {
        Message message = new Message(text, tag);
        messageRepo.save(message);
        return new RedirectView("");
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
