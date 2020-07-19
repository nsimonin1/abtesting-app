package org.afrinnov.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
@Getter
public class AbtestApplicationProperties {
    private final Abtest abtest = new Abtest();

    @Getter
    @Setter
    public static class Abtest {
        private boolean ponderationStrategy;
    }
}
