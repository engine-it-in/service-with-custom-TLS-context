package ru.nikitinia.odm.bki.config.service.marshaller;

import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BKIMarshaller {

    public Jaxb2Marshaller prepareMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(NAME_BKI_DATA_STRUCTURE);
        return marshaller;
    }

}
