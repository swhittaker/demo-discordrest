package uk.co.samwhittaker.demo.discordrest.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.samwhittaker.demo.discordrest.service.MessageService;

import java.util.List;

/**
 * Rest controller which allows messages to be submitted and displays received messages
 */
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService service;

    @GetMapping(value = "/messages")
    public List<String> getMessages() {
        return service.getMessages();
    }

    @PostMapping("/messages")
    public void sendMessage(@RequestParam String message) {
        service.handleReceivedMessage(message);
    }

    @GetMapping(value = "/last-sent")
    public long getLastSent() {
        return service.getTimeLastMessageSent().toEpochMilli();
    }


}
