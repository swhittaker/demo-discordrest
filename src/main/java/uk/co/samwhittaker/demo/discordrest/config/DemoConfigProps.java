package uk.co.samwhittaker.demo.discordrest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * Properties for the demo application
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "uk.co.samwhittaker.demo.discordrest")
public class DemoConfigProps {

    private String discordCommandPrefix;
    private String discordToken;
    private String activity;

    public Optional<String> getActivity() {
        return Optional.ofNullable(activity);
    }
}
