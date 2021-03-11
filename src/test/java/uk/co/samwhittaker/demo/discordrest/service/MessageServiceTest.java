package uk.co.samwhittaker.demo.discordrest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.samwhittaker.demo.discordrest.storage.InMemoryStorage;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private DiscordService discordService;

    @Mock
    private InMemoryStorage storage;

    @InjectMocks
    private MessageService messageService;

    @Test
    void handleReceivedMessage() {
        Instant start = Instant.now();
        String message = "someMessage";

        messageService.handleReceivedMessage(message);

        ArgumentCaptor<Instant> argumentCaptor = ArgumentCaptor.forClass(Instant.class);
        verify(discordService).sendMessage(message);
        verify(storage).setTimeLastMessageSent(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue()).isBetween(start, Instant.now());
    }
}