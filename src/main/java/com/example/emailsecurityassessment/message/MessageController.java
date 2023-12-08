package com.example.emailsecurityassessment.message;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    private final MessageService messageService;
    private boolean cooldown = true;
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public void getMessage() {
        messageService.newMessage();
    }

    @GetMapping("/on")
    public void turnOnPushNotifications() {
        messageService.requestPushNotifications();
    }

    @GetMapping("/off")
    public void turnOffPushNotifications() {
        messageService.stopPushNotifications();
    }

    @PostMapping
    public void postMessage(@RequestBody String body) {
        System.out.println("Method POST triggered!");
        System.out.println(body);
        if (cooldown) {
            cooldown = false;
            System.out.println("Method messageService.newMessage() executed!");
            messageService.newMessage();
            executorService.schedule(() -> {
                cooldown = true;
            }, 5, TimeUnit.SECONDS);
        }
    }
}
