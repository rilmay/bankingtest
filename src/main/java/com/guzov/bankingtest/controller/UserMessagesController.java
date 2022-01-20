package com.guzov.bankingtest.controller;

import com.guzov.bankingtest.domain.Message;
import com.guzov.bankingtest.domain.User;
import com.guzov.bankingtest.repository.MessageRepo;
import com.guzov.bankingtest.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class UserMessagesController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private MessageService messageService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Map<String, Object> model,
            @RequestParam(required = false) Message message,
            @PageableDefault(sort = {"id"}, direction =Sort.Direction.DESC) Pageable pageable
    ) {

        Page<Message> messages = messageService.messageListForUser(pageable, user);

        model.put("page", messages);
        model.put("message", message);
        model.put("userChannel", user);
        model.put("subscriptionsCount", user.getSubscriptions().size());
        model.put("subscribersCount", user.getSubscribers().size());
        model.put("isSubscriber", user.getSubscribers().contains(currentUser));
        model.put("url", "/user-messages/" + user.getId());

        model.put("isCurrentUser", currentUser.equals(user));
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file

    ) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            message.setText(text);
            message.setTag(tag);


            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                message.setFileName(resultFilename);
            }

            messageRepo.save(message);
        }
        return "redirect:/user-messages/" + user;
    }
}
