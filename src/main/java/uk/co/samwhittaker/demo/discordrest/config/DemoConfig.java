package uk.co.samwhittaker.demo.discordrest.config;

import disparse.discord.jda.Dispatcher;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * Configuration for the demo application
 */
@Configuration
@Slf4j
public class DemoConfig {

    @Bean
    public JDA getJDA(DemoConfigProps props, Optional<List<ListenerAdapter>> listeners)
            throws InterruptedException, LoginException {

        Dispatcher.Builder dispatcherBuilder = new Dispatcher.Builder(DemoConfig.class)
                .description("Example discord bot!")
                .prefix(props.getDiscordCommandPrefix())
                .pageLimit(10)
                .withExecutorService(Executors.newFixedThreadPool(10));

        JDABuilder jdaBuilder = Dispatcher.init(
                JDABuilder.create(props.getDiscordToken(), GatewayIntent.getIntents(GatewayIntent.GUILD_MESSAGES.getRawValue())),
                dispatcherBuilder.build());
        listeners.ifPresent(jdaBuilder::addEventListeners);
        JDA jda = jdaBuilder.build();
        jda.awaitReady();
        props.getActivity().ifPresent(activity -> jda.getPresence().setActivity(Activity.listening(activity)));
        return jda;
    }


}
