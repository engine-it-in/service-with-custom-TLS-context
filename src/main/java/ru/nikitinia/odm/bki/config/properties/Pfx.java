package ru.nikitinia.odm.bki.config.properties;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties(value = "pfx")
public record Pfx(

        String passwordPFX

) {
}
