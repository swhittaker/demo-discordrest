package uk.co.samwhittaker.demo.discordrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.samwhittaker.demo.discordrest.storage.InMemoryStorage;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final InMemoryStorage storage;

    private final DiscordService discordService;

    public void handleReceivedMessage(String message){
        discordService.sendMessage(message);
        storage.setTimeLastMessageSent(Instant.now());
    }

    public Instant getTimeLastMessageSent() {
        return storage.getTimeLastMessageSent();
    }

    public List<String> getMessages() {
        return storage.getMessages();
    }
}
