package ru.nikitinia.odm.bki.exception.bki;

import lombok.Getter;
import ru.nikitinia.odm.bki.model.exception.ServiceModelException;

@Getter
public class BKIProcessingException extends RuntimeException {
    private final transient ServiceModelException serviceModelException;

    public BKIProcessingException(ServiceModelException serviceModelException) {
        super();
        this.serviceModelException = serviceModelException;
    }

}
