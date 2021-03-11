package uk.co.samwhittaker.demo.discordrest.service;


import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.stereotype.Service;
import uk.co.samwhittaker.demo.discordrest.storage.InMemoryStorage;

/**
 * Service which provides discord-specific behaviour
 *
 * Hard coded to use a particular "guild" (server) name and channel
 */
@Service
@RequiredArgsConstructor
public class DiscordService {
    private final JDA jda;

    private final InMemoryStorage inMemoryStorage;

    public void sendMessage(String message){
        TextChannel channel = getChannel(jda, "bot test", "demo-channel");
        channel.sendMessage(new MessageBuilder().setContent(message).build()).queue();
    }

    private static TextChannel getChannel(JDA jda, String guildName, String channelName){
        return jda.getGuildsByName(guildName, false)
                .stream()
                .findFirst()
                .orElseThrow()
                .getTextChannels()
                .stream()
                .filter(channel -> channel.getName().equals(channelName))
                .findFirst()
                .orElseThrow();
    }

    public void pong(MessageChannel channel) {
        channel.sendMessage("pong").queue();
    }

    public void logMessage(String message) {
        inMemoryStorage.addMessage(message);
    }
}
