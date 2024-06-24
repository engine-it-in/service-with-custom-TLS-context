package ru.nikitinia.odm.bki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import static ru.nikitinia.odm.bki.util.cryptopro.AdderProviderCryptoPro.addProvideDataCryptoProForMoreOrEquals10JVM;



@ConfigurationPropertiesScan
@SpringBootApplication
public class ServiceWithCustomTLSContext extends SpringBootServletInitializer {

    static {
        addProvideDataCryptoProForMoreOrEquals10JVM();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ServiceWithCustomTLSContext.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceWithCustomTLSContext.class, args);
    }
}