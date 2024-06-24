package ru.nikitinia.odm.bki.config.service.client;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import ru.nikitinia.odm.bki.config.tls.context.SSLContextConfig;

@Configuration
@RequiredArgsConstructor
public class HttpClientConfig {

    private final SSLContextConfig contextConfig;

    public HttpClient prepareHttpClient() {

        return HttpClients.custom()
                // set clean double header (generated data structure and webservice add)
                .addInterceptorFirst(new HttpComponentsMessageSender.RemoveSoapHeadersInterceptor())
                // set custom context for cryptoPro crypt traffic
                .setSSLContext(contextConfig.getInstance(true))
                .build();
    }

}
