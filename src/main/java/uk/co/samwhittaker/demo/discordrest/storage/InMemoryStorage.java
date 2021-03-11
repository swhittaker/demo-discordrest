package uk.co.samwhittaker.demo.discordrest.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class InMemoryStorage {

    private final List<String> messages = new ArrayList<>();

    private Instant timeLastMessageSent;

    private String lastMessageReceived;


    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
