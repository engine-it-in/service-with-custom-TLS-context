package ru.nikitinia.odm.bki.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nikitinia.odm.bki.config.service.client.WebServiceTemplateConfiguration;
import ru.nikitinia.odm.bki.exception.BKIIncomeWebException;
import ru.nikitinia.odm.bki.model.bki.request.Request;
import ru.nikitinia.odm.bki.model.bki.response.Response;

@Service
public class BKIIncomeClient {

    @Value("${service.bki}")
    private String serviceUrl;

    private final WebServiceTemplateConfiguration webServiceTemplate;

    public BKIIncomeClient(WebServiceTemplateConfiguration webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public Response postMessageToBKI(Request request) {
        try {
            return (Response) webServiceTemplate
                    .prepareWebServiceTemplateForBKIIncomeSend(serviceUrl)
                    .marshalSendAndReceive(request);
        } catch (Exception e) {
            throw new BKIIncomeWebException(e.getMessage());
        }

    }

}