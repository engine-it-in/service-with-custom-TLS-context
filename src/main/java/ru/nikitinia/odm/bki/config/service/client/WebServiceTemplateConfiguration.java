package ru.nikitinia.odm.bki.config.service.client;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import ru.nikitinia.odm.bki.config.service.marshaller.BKIMarshaller;

@Getter
@Configuration
public class WebServiceTemplateConfiguration {

    private final HttpClientConfig httpClientConfig;
    private final WebServiceTemplate webServiceTemplate;
    private final HttpComponentsMessageSender messageSender;
    private final BKIMarshaller BKIMarshaller;

    public WebServiceTemplateConfiguration(HttpClientConfig httpClientConfig, BKIMarshaller BKIMarshaller) {
        this.httpClientConfig = httpClientConfig;
        this.BKIMarshaller = BKIMarshaller;
        this.webServiceTemplate = new WebServiceTemplate();
        this.messageSender = new HttpComponentsMessageSender();
    }

    public WebServiceTemplate prepareWebServiceTemplateForBKIIncomeSend(String uri) {
        messageSender.setHttpClient(httpClientConfig.prepareHttpClient());
        webServiceTemplate.setMessageSender(messageSender);
        webServiceTemplate.setMarshaller(BKIMarshaller.prepareMarshaller());
        webServiceTemplate.setUnmarshaller(BKIMarshaller.prepareMarshaller());
        webServiceTemplate.setDefaultUri(uri);
        return webServiceTemplate;
    }

}
