package uk.co.samwhittaker.demo.discordrest.api.discord;

import disparse.discord.jda.DiscordRequest;
import disparse.parser.reflection.CommandHandler;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import uk.co.samwhittaker.demo.discordrest.service.DiscordService;

import javax.annotation.PostConstruct;

/**
 * Class to manage commands received from discord
 *
 * Static class due to behaviour of third party library
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscordCommandController {

    private static DiscordService discordService;

    @CommandHandler(commandName = "ping")
    public static void pingVerbose(DiscordRequest req) {
        discordService.pong(req.getEvent().getChannel());
    }

    @CommandHandler(commandName = "log")
    public static void logMessage(DiscordRequest req) {
        if(!req.getArgs().isEmpty()) {
            discordService.logMessage(Strings.join(req.getArgs(), ' '));
        }
    }

    /**
     * Helper class which gets the DiscordService dependency and sets it to the outer classes variable
     */
    @Component
    @RequiredArgsConstructor
    public static class DiscordControllerSetupHelper{
        private final DiscordService discordService;

        @PostConstruct
        private void setup(){
            DiscordCommandController.discordService = discordService;
        }

    }
}
